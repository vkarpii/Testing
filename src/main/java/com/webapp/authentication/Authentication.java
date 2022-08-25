package com.webapp.authentication;

import com.webapp.database.user.UserDAO;
import com.webapp.entity.User;
import com.webapp.exeption.IllegalEmailException;
import com.webapp.exeption.IllegalInputException;
import com.webapp.exeption.recaptcha.IllegalReCaptchaException;
import com.webapp.exeption.user.BlockedException;
import com.webapp.exeption.user.IllegalPasswordException;
import com.webapp.recaptcha.VerifyRecaptcha;
import com.webapp.security.AES;
import com.webapp.valid.EmailValidator;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

import static com.webapp.entity.Role.STUDENT;

@Slf4j
public class Authentication {
    private Authentication(){}
    public static User register(String login,String password, String repeatPassword,String gRecaptchaResponse,String email,String name, String surname) throws IllegalInputException, IllegalPasswordException, IllegalEmailException, IllegalReCaptchaException {
        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
        boolean validate = EmailValidator.validate(email);
        String encryptPassword = AES.encrypt(password);
        boolean checkPass = encryptPassword.equals(AES.encrypt(repeatPassword));
        if (!name.chars().allMatch(Character::isLetter))
            throw new IllegalInputException("Name can`t consist digits.");
        if (!surname.chars().allMatch(Character::isLetter))
            throw new IllegalInputException("Surname can`t consist digits.");
        if (!checkPass)
            throw new IllegalPasswordException("Passwords do not match.");
        if (!validate)
            throw new IllegalEmailException("Incorrect mail.");
        if (!verify)
            throw new IllegalReCaptchaException("You mussed the Captcha.");
        User newUser = new User(login,name,encryptPassword,surname,email,STUDENT,false);
        UserDAO userDAO = UserDAO.getInstance();
        userDAO.create(newUser);
        userDAO.addEntityToGroupAll(newUser);
        ArrayList<String> groups = new ArrayList<>();
        groups.add("all");
        newUser.setGroups(groups);
        log.info("User {} register.",login);
        return newUser;
    }
    public static User login(String login,String password,String gRecaptchaResponse) throws IllegalPasswordException, IllegalReCaptchaException, BlockedException {
        String encryptPassword = AES.encrypt(password);
        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
        User user = UserDAO.getInstance().getEntityByString(login,encryptPassword);
        if (user != null && verify){
            if (user.isBlocked())
                throw new BlockedException("User blocked");
            return user;
        }
        if (verify)
            throw new IllegalPasswordException("Either login or password is wrong.");
        else
            throw new IllegalReCaptchaException("You mussed the Captcha.");


    }
}
