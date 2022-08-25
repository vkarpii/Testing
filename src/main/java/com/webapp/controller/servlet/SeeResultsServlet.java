package com.webapp.controller.servlet;

import com.webapp.database.user.UserDAO;
import com.webapp.entity.Result;
import com.webapp.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/seeResults")
public class SeeResultsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUser = Integer.parseInt(req.getParameter("id"));
        User user = new User();
        user.setId(idUser);
        ArrayList<Result> results = UserDAO.getInstance().getEntityResult(user);
        req.setAttribute("userResults",results);
        req.getRequestDispatcher("/userresult.jsp")
                .forward(req,resp);
    }
}
