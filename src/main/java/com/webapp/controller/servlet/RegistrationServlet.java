package com.webapp.controller.servlet;

import com.webapp.authentication.Authentication;
import com.webapp.database.group.GroupDAO;
import com.webapp.database.subject.SubjectDAO;
import com.webapp.entity.Group;
import com.webapp.entity.Subject;
import com.webapp.entity.User;
import com.webapp.exeption.IllegalEmailException;
import com.webapp.exeption.IllegalInputException;
import com.webapp.exeption.recaptcha.IllegalReCaptchaException;
import com.webapp.exeption.user.IllegalPasswordException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("psw");
        String passwordCheck = req.getParameter("pswcheck");
        String login = req.getParameter("ulogin");
        String name = req.getParameter("uname");
        String surname = req.getParameter("usurname");
        String email = req.getParameter("uemail");
        String gRecaptchaResponse = req.getParameter("g-recaptcha-response");

        String loginError;
        try {
            User newUser = Authentication.register(login, password, passwordCheck, gRecaptchaResponse, email, name, surname);
            req.getSession().setAttribute("login", "loggined");
            req.getSession().setAttribute("user", newUser);
            System.out.println("LoginServlet#doPost|" + "InputData: Login: " + login + " Password: " + password + " Name: " + name + " Surname: " + surname
                    + " Email: " + email);
            //EmailSender.sendEmail(newUser.getEmail(), "Resitration",newUser.toString());
            ArrayList<Group> groups = GroupDAO.getInstance().getAll();
            req.getSession().setAttribute("groups",groups);

            ArrayList<Subject> subjects = SubjectDAO.getInstance().getAll();
            req.getSession().setAttribute("registerAccount","You`r account was successfully created!");
            req.getSession().setAttribute("subjects",subjects);
            resp.sendRedirect(req.getContextPath() + "/main?page=1");
            return;
        } catch (IllegalInputException | IllegalPasswordException | IllegalEmailException |
                 IllegalReCaptchaException e) {
            loginError = e.getMessage();
        }
        req.setAttribute("login", loginError);
        req.setAttribute("loginInput", login);
        req.setAttribute("emailInput", email);
        req.setAttribute("nameInput", name);
        req.setAttribute("surnameInput", surname);
        req.getRequestDispatcher("/registration.jsp")
                .forward(req, resp);
    }
}
