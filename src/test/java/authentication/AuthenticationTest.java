package authentication;

import com.webapp.authentication.Authentication;
import com.webapp.database.user.UserDAO;
import com.webapp.entity.Role;
import com.webapp.entity.User;
import com.webapp.exeption.IllegalEmailException;
import com.webapp.exeption.IllegalInputException;
import com.webapp.exeption.recaptcha.IllegalReCaptchaException;
import com.webapp.exeption.user.BlockedException;
import com.webapp.exeption.user.IllegalPasswordException;
import com.webapp.recaptcha.VerifyRecaptcha;
import com.webapp.security.AES;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationTest {
    private String login;
    private String password;
    private String repeatPassword;
    private String recaptcha;
    private String email;
    private String name;
    private String surname;
    @Mock
    UserDAO instance;


    @Test
    void authenticationIllegalNameExceptionTest(){
        assertThrows(IllegalInputException.class,
                () ->{
                    name = "withDigits12312";
                    email = "";
                    password = "";
                    repeatPassword = "";
                    Authentication.register(login,password,repeatPassword,recaptcha,email, name,surname);
                });
    }
    @Test
    void authenticationIllegalSurnameExceptionTest(){
        assertThrows(IllegalInputException.class,
                () ->{
                    name = "Truename";
                    email = "";
                    password = "";
                    repeatPassword = "";
                    surname = "withDigits12312";
                    Authentication.register(login,password,repeatPassword,recaptcha,email, name,surname);
                });
    }
    @Test
    void authenticationIllegalPasswordExceptionTest(){
        assertThrows(IllegalPasswordException.class,
                () ->{
                    name = "Truename";
                    password = "12345";
                    repeatPassword = "54321";
                    surname = "Truesurname";
                    email ="";
                    Authentication.register(login,password,repeatPassword,recaptcha,email, name,surname);
                });
    }
    @Test
    void authenticationIllegalEmailExceptionTest(){
        assertThrows(IllegalEmailException.class,
                () ->{
                    name = "Truename";
                    email = "wrongemail";
                    password = "12345";
                    repeatPassword = "12345";
                    surname = "Truesurname";
                    Authentication.register(login,password,repeatPassword,recaptcha,email, name,surname);
                });
    }
    @Test
    void authenticationIllegalRecaptchaExceptionTest(){
        assertThrows(IllegalReCaptchaException.class,
                () ->{
                    name = "Truename";
                    email = "example@gmail.com";
                    password = "12345";
                    repeatPassword = "12345";
                    surname = "Truesurname";
                    Authentication.register(login,password,repeatPassword,recaptcha,email, name,surname);
                });
    }
    @Test
    void successRegistration(){
        User user = null;
        try(MockedStatic<VerifyRecaptcha> mockedCaptcha = Mockito.mockStatic(VerifyRecaptcha.class);
            MockedStatic<UserDAO> mockedDAO = Mockito.mockStatic(UserDAO.class)) {
            login = "test";
            password = "12345";
            repeatPassword = password;
            email = "example@gmail.com";
            name = "Name";
            surname = "Surname";
            User newUser = new User(login,name, AES.encrypt(password),surname,email, Role.STUDENT,false);
            mockedCaptcha.when(() ->
                    VerifyRecaptcha.verify(recaptcha)).thenReturn(true);
            mockedDAO.when(UserDAO::getInstance).thenReturn(instance);
            mockedDAO.when(() ->
                    instance.create(newUser)).thenReturn(true);
            mockedDAO.when(() ->
                    instance.addEntityToGroupAll(newUser)).thenReturn(true);
            user = Authentication.register(login,password,repeatPassword,recaptcha,email,name,surname);
        } catch (IllegalInputException | IllegalPasswordException | IllegalEmailException |
                 IllegalReCaptchaException e) {
            Assertions.fail();
        }
        assertEquals(login,user.getLogin());
        assertNotEquals(password,user.getPassword());
        assertEquals(email,user.getEmail());
        assertEquals(name,user.getFirst_name());
        assertEquals(surname,user.getLast_name());
    }
    @Test
    void loginIllegalPasswordExceptionTest(){
        assertThrows(IllegalPasswordException.class,
                () ->{
                    try (MockedStatic<VerifyRecaptcha> mockedCaptcha = Mockito.mockStatic(VerifyRecaptcha.class);
                         MockedStatic<UserDAO> mocked = Mockito.mockStatic(UserDAO.class)) {
                        mockedCaptcha.when(() ->
                                VerifyRecaptcha.verify(recaptcha)).thenReturn(true);
                        mocked.when(UserDAO::getInstance).thenReturn(instance);
                        login = "test";
                        password = "12345";
                        repeatPassword = password;
                        email = "example@gmail.com";
                        name = "Name";
                        surname = "Surname";
                        User newUser = new User(login,name, AES.encrypt(password),surname,email, Role.STUDENT,false);
                        mocked.when(() ->
                                instance.getEntityByString(newUser.getLogin(),newUser.getPassword())).thenReturn(null);
                        Authentication.login(login,password,recaptcha);
                    }
                });
    }
    @Test
    void loginIllegalRecaptchaExceptionTest(){
        assertThrows(IllegalReCaptchaException.class,
                () ->{
                    try (MockedStatic<UserDAO> mocked = Mockito.mockStatic(UserDAO.class)) {
                        mocked.when(UserDAO::getInstance).thenReturn(instance);
                        login = "test";
                        password = "12345";
                        repeatPassword = password;
                        email = "example@gmail.com";
                        name = "Name";
                        surname = "Surname";
                        User newUser = new User(login,name, AES.encrypt(password),surname,email, Role.STUDENT,false);
                        mocked.when(() ->
                                instance.getEntityByString(newUser.getLogin(),newUser.getPassword())).thenReturn(null);
                        Authentication.login(login,password,recaptcha);
                    }
                });
    }
    @Test
    void loginBlockedUserTest(){
        assertThrows(BlockedException.class,
                () ->{
                    try (MockedStatic<VerifyRecaptcha> mockedCaptcha = Mockito.mockStatic(VerifyRecaptcha.class);
                            MockedStatic<UserDAO> mocked = Mockito.mockStatic(UserDAO.class)) {
                        mockedCaptcha.when(() ->
                                VerifyRecaptcha.verify(recaptcha)).thenReturn(true);
                        mocked.when(UserDAO::getInstance).thenReturn(instance);
                        login = "test";
                        password = "12345";
                        repeatPassword = password;
                        email = "example@gmail.com";
                        name = "Name";
                        surname = "Surname";
                        User newUser = new User(login,name, AES.encrypt(password),surname,email, Role.STUDENT, true);
                        mocked.when(() ->
                                instance.getEntityByString(newUser.getLogin(),newUser.getPassword())).thenReturn(newUser);
                        Authentication.login(login,password,recaptcha);
                    }
                });
    }
    @Test
    void successLoginTest(){
        User loginUser = null;
        User user = null;
        try (MockedStatic<VerifyRecaptcha> mockedCaptcha = Mockito.mockStatic(VerifyRecaptcha.class);
             MockedStatic<UserDAO> mocked = Mockito.mockStatic(UserDAO.class)) {
            mockedCaptcha.when(() ->
                    VerifyRecaptcha.verify(recaptcha)).thenReturn(true);
            mocked.when(UserDAO::getInstance).thenReturn(instance);
            login = "test";
            password = "12345";
            repeatPassword = password;
            email = "example@gmail.com";
            name = "Name";
            surname = "Surname";
            User newUser = new User(login,name, AES.encrypt(password),surname,email, Role.STUDENT, false);
            mocked.when(() ->
                    instance.getEntityByString(newUser.getLogin(),newUser.getPassword())).thenReturn(newUser);
            loginUser = Authentication.login(login,password,recaptcha);
            user = newUser;
        } catch (IllegalPasswordException | IllegalReCaptchaException | BlockedException e) {
            Assertions.fail();
        }
        assertEquals(login,loginUser.getLogin());
        assertEquals(email,loginUser.getEmail());
        assertEquals(loginUser,user);
    }

}
