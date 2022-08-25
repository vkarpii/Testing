package com.webapp.controller.servlet;

import com.webapp.database.user.UserDAO;
import com.webapp.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//checked
@WebServlet("/EditUser")
public class EditUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        UserDAO userDAO = UserDAO.getInstance();

        String login = req.getParameter("ulogin");
        String name = req.getParameter("uname");
        String surname = req.getParameter("usurname");
        String email = req.getParameter("uemail");

        if (user.getLogin().equals(login) && user.getFirst_name().equals(name) &&
        user.getLast_name().equals(surname) && user.getEmail().equals(email)){
            req.setAttribute("error","You haven't changed anything!");
            req.getRequestDispatcher("/edituser.jsp")
                    .forward(req,resp);
            return;
        }
        if (userDAO.checkEntityByLogin(login) && !user.getLogin().equals(login)){
            req.setAttribute("error","This login already exist!");
            req.getRequestDispatcher("/edituser.jsp")
                    .forward(req,resp);
            return;
        }
        user.setLogin(login);
        user.setFirst_name(name);
        user.setLast_name(surname);
        user.setEmail(email);
        userDAO.update(user);
        resp.sendRedirect(req.getContextPath() + "/info.jsp");
    }
}
