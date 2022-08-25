package com.webapp.controller.servlet;

import com.webapp.entity.Result;
import com.webapp.entity.User;
import com.webapp.database.user.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/InfoServlet")
public class InfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("testName");
        User user = (User) req.getSession().getAttribute("user");
        ArrayList<Result> results = UserDAO.getInstance().getEntityResult(user);
        req.getSession().setAttribute("results",results);
        resp.sendRedirect(req.getContextPath() + "/info.jsp");
    }
}
