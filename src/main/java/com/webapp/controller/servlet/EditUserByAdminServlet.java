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
@WebServlet("/editUserByAdmin")
public class EditUserByAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = UserDAO.getInstance().getEntityById(id);
        //System.out.println(user);
        req.getSession().setAttribute("editUser",user);
        req.getRequestDispatcher("edituserbyadmin.jsp")
                        .forward(req,resp);
        //resp.sendRedirect(req.getContextPath() + "/edit.jsp");
    }
}
