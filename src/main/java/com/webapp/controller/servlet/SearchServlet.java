package com.webapp.controller.servlet;

import com.webapp.PaginationBuilder;
import com.webapp.database.test.TestDAO;
import com.webapp.entity.Test;
import com.webapp.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pattern = "%" + req.getParameter("search") + "%'";
        TestDAO testDAO = TestDAO.getInstance();
        int noOfRecords = testDAO.getCountByLIKE(pattern);
        PaginationBuilder paginationBuilder = new PaginationBuilder(req.getParameter("page"),noOfRecords);
        req.setAttribute("noOfPages", paginationBuilder.getNumberOfPages());
        req.setAttribute("currentPage", paginationBuilder.getPage());
        User user = (User) req.getSession().getAttribute("user");
        int id = user.getId();
        ArrayList<Test> tests = testDAO.getLimitByLIKE((paginationBuilder.getPage() - 1)* paginationBuilder.getCountOnPage(),
                paginationBuilder.getCountOnPage(),pattern,id,user.getRole().getId());
        //System.out.println(tests);
        req.getSession().setAttribute("tests",tests);
        req.getRequestDispatcher("/index.jsp")
                        .forward(req,resp);
    }
}
