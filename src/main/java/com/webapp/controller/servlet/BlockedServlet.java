package com.webapp.controller.servlet;

import com.webapp.database.user.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//checked
@WebServlet("/block")
public class BlockedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUser = Integer.parseInt(req.getParameter("id"));
        String event = req.getParameter("event");
        UserDAO.getInstance().changeBlock(idUser,event);
        req.getRequestDispatcher("/AdminPanelServlet?page=1")
                .forward(req,resp);
    }
}
