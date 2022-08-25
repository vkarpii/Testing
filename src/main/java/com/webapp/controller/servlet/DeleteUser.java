package com.webapp.controller.servlet;

import com.webapp.database.user.UserDAO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet("/deleteUser")
public class DeleteUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUser = Integer.parseInt(req.getParameter("id"));
        UserDAO.getInstance().delete(idUser);
        log.info("Delete user id = {}",idUser);
        req.getRequestDispatcher("/AdminPanelServlet?page=1")
                .forward(req,resp);
    }
}
