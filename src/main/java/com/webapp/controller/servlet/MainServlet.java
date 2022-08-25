package com.webapp.controller.servlet;

import com.webapp.EmailSender;
import com.webapp.PaginationBuilder;
import com.webapp.database.MainQueryBuilder;
import com.webapp.entity.Test;
import com.webapp.database.test.TestDAO;
import com.webapp.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
//refactored
@WebServlet("/main")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("change");
        req.getSession().removeAttribute("testName");
        TestDAO testDAO = TestDAO.getInstance();
        ArrayList<String> results = (ArrayList<String>) req.getSession().getAttribute("res");
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            String filter = null;
            if (req.getParameter("filter") != null)
                filter = req.getParameter("filter");
            //int noOfRecords = testDAO.getCount();
            PaginationBuilder paginationBuilder = new PaginationBuilder(req.getParameter("page"));
            if (req.getParameter("changePages") != null)
                paginationBuilder.setCountOnPage(Integer.parseInt(req.getParameter("changePages")));
            else if (req.getSession().getAttribute("countOnPage") != null)
                paginationBuilder.setCountOnPage((Integer) req.getSession().getAttribute("countOnPage"));
            String query = MainQueryBuilder.queryBuild(results,user,
                    (paginationBuilder.getPage() - 1)* paginationBuilder.getCountOnPage(),
                    paginationBuilder.getCountOnPage(),
                    filter);
            ArrayList<Test> tests = testDAO.getLimit(query);
            paginationBuilder.setCountOfElement(testDAO.getCount());
            //System.out.println(tests);
            req.setAttribute("noOfPages", paginationBuilder.getNumberOfPages());
            req.setAttribute("currentPage", paginationBuilder.getPage());
            req.getSession().setAttribute("countOnPage",paginationBuilder.getCountOnPage());
            req.getSession().setAttribute("tests",tests);
            req.getSession().removeAttribute("check");
        }
        req.getRequestDispatcher("/index.jsp")
                .forward(req, resp);
    }
}
