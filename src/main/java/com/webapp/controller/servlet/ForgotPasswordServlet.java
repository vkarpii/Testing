package com.webapp.controller.servlet;

import com.webapp.EmailSender;
import com.webapp.database.user.UserDAO;
import com.webapp.entity.User;
import com.webapp.security.AES;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/forgotPassword")
public class ForgotPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = AES.decrypt(req.getParameter("login"));
        req.getSession().removeAttribute("newPass");
        req.getSession().setAttribute("newPass",login);
        req.getRequestDispatcher("createnewpass.jsp")
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        User user = UserDAO.getInstance().getUserByLogin(login);
        if (user != null){
            String email = user.getEmail();
            String password = user.getPassword();
            String subject = "<h3>Recovery password</h3>";
            String text = "For recovery your password go here :" + req.getRequestURL().toString() + "?login="+ AES.encrypt(login);
            EmailSender.sendEmail(email,subject,text);
            System.out.println("Letter was sended!");
            resp.sendRedirect(req.getContextPath() + "/emailsended.jsp");
        } else {
            req.setAttribute("error","Wrong login!");
            req.getRequestDispatcher("/forgot.jsp")
                    .forward(req,resp);
        }

    }
}
