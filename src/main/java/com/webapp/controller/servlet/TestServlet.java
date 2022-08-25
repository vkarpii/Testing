package com.webapp.controller.servlet;

import com.webapp.TestSortingService;

import com.webapp.entity.Test;
import com.webapp.entity.User;
import com.webapp.database.test.TestDAO;
import com.webapp.database.user.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idTest = Integer.parseInt(req.getParameter("id"));
        TestDAO testDAO = TestDAO.getInstance();
        Test test = testDAO.getEntityById(idTest);
        testDAO.getQuestionsForTest(test);
        testDAO.getAnswersForQuestions(test);

        test = TestSortingService.sorting(test);
        User user = (User) req.getSession().getAttribute("user");
        int attemps = UserDAO.getInstance().getAttemps(test,user);
        req.getSession().setAttribute("CurrAttemps",attemps);

        req.getSession().setAttribute("Test",test);
        req.getRequestDispatcher("/starttest.jsp")
                        .forward(req,resp);
        //resp.sendRedirect(req.getContextPath() + "/starttest.jsp");
    }
}
