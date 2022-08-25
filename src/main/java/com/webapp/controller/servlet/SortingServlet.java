package com.webapp.controller.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/SortingServlet")
public class SortingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<String> names = new ArrayList<>();
        names.add("test_name");
        names.add("complexity");
        names.add("number_of_queries");

        ArrayList<String> results = new ArrayList<>();
        for (int i = 0;i!=names.size();i++)
            results.add(req.getParameter(names.get(i)));
        req.getSession().setAttribute("res",results);
        req.getRequestDispatcher("/main?page=1")
                .forward(req, resp);
    }
}
