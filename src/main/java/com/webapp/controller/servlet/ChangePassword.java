package com.webapp.controller.servlet;

import com.webapp.database.user.UserDAO;
import com.webapp.entity.User;
import com.webapp.security.AES;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//checked
@WebServlet("/changePassword")
public class ChangePassword extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newPassword = AES.encrypt(req.getParameter("newpass"));
        String repNewPassword = AES.encrypt(req.getParameter("repnewpass"));

        UserDAO userDAO = UserDAO.getInstance();
        User user;
        String login = (String) req.getSession().getAttribute("newPass");
        User checkUser;
        boolean checkOldPass = true;
        if (login == null) {
            String oldPassword = AES.encrypt(req.getParameter("oldpass"));
            user = (User) req.getSession().getAttribute("user");
            checkUser = userDAO.getEntityByString(user.getLogin(), oldPassword);
            checkOldPass = checkUser != null;
        } else {
            user = userDAO.getUserByLogin(login);
        }
        //перевірка чи два нові паролі однакові
        boolean checkNewPass = newPassword.equals(repNewPassword);
        //перевірка чи старий пароль такий самий як новий
        checkUser = userDAO.getEntityByString(user.getLogin(), newPassword);
        boolean checkNewWithOld = checkUser == null;
        if (checkNewPass && checkOldPass && checkNewWithOld) {
            userDAO.updatePassword(user.getLogin(), newPassword);
            System.out.println("Password was changes");
            if (login != null) {
                resp.sendRedirect(req.getContextPath() + "/main?page=1");
                return;
            }
            resp.sendRedirect(req.getContextPath() + "/InfoServlet");
            return;
        }
        String error;
        if (checkOldPass)
            error = "The old password was entered incorrectly";
        else if (checkNewPass)
            error = "Passwords do not match";
        else
            error = "The new password matches the old one";
        req.setAttribute("error", error);
        if (login == null)
            req.getRequestDispatcher("/changepass.jsp")
                    .forward(req, resp);
        else
            req.getRequestDispatcher("/createnewpass.jsp")
                .forward(req, resp);
    }
}
