package com.webapp.controller.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("user");
        req.getSession().removeAttribute("login");
        req.getSession().removeAttribute("results");
        req.getSession().removeAttribute("countOnPage");
        req.getSession().removeAttribute("tests");
        req.getRequestDispatcher("/index.jsp")
                        .forward(req,resp);
        //resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }

}
