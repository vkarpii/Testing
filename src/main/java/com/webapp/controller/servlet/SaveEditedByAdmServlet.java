package com.webapp.controller.servlet;

import com.webapp.database.user.UserDAO;
import com.webapp.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SaveEditedByAdmServlet")
public class SaveEditedByAdmServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("ulogin");
        String name = req.getParameter("uname");
        String surname = req.getParameter("usurname");
        String email = req.getParameter("uemail");
        User editUser = (User) req.getSession().getAttribute("editUser");

        UserDAO userDAO = UserDAO.getInstance();

        if (editUser.getLogin().equals(login) && editUser.getFirst_name().equals(name) &&
                editUser.getLast_name().equals(surname) && editUser.getEmail().equals(email)){
            req.setAttribute("error","You haven't changed anything!");
            req.getRequestDispatcher("/edituserbyadmin.jsp")
                    .forward(req,resp);
            return;
        }
        if (userDAO.checkEntityByLogin(login) && !editUser.getLogin().equals(login)){
            req.setAttribute("error","This login already exist!");
            req.getRequestDispatcher("/edituser.jsp")
                    .forward(req,resp);
            return;
        }

        editUser.setLogin(login);
        editUser.setFirst_name(name);
        editUser.setLast_name(surname);
        editUser.setEmail(email);
        userDAO.update(editUser);
        resp.sendRedirect(req.getContextPath() + "//AdminPanelServlet?page=1");
    }
}
