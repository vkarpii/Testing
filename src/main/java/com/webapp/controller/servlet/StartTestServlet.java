package com.webapp.controller.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/startTest")
public class StartTestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date date = new Date();
        req.getSession().setAttribute("time",date.getTime());
        req.getSession().setAttribute("reload",0);
        resp.sendRedirect(req.getContextPath() + "/test.jsp");
    }
}
