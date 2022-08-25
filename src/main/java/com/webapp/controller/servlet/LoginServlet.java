package com.webapp.controller.servlet;


import com.webapp.authentication.Authentication;
import com.webapp.database.group.GroupDAO;
import com.webapp.database.subject.SubjectDAO;
import com.webapp.entity.Group;
import com.webapp.entity.Subject;
import com.webapp.entity.User;
import com.webapp.exeption.recaptcha.IllegalReCaptchaException;
import com.webapp.exeption.user.BlockedException;
import com.webapp.exeption.user.IllegalPasswordException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

//refactored
@Slf4j
@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("login");

        String login = req.getParameter("ulogin");
        String password = req.getParameter("psw");
        String gRecaptchaResponse = req.getParameter("g-recaptcha-response");

        try {
            User user = Authentication.login(login,password,gRecaptchaResponse);
            req.getSession().setAttribute("user",user);
            log.info("User '{}' loggined.",user.getName());
            ArrayList<Group> groups = GroupDAO.getInstance().getAll();
            req.getSession().setAttribute("groups",groups);

            ArrayList<Subject> subjects = SubjectDAO.getInstance().getAll();
            req.getSession().setAttribute("subjects",subjects);
            //--?
            resp.sendRedirect( req.getContextPath() + "/main?page=1");
        } catch (IllegalPasswordException | IllegalReCaptchaException e) {
            req.setAttribute("login", e.getMessage());
            req.setAttribute("loginInput",login);
            req.getRequestDispatcher("/login.jsp")
                    .forward(req,resp);
        } catch (BlockedException e){
            req.setAttribute("blockedInfo",login);
            req.getRequestDispatcher("/blocked.jsp")
                            .forward(req,resp);
        }
    }
}

