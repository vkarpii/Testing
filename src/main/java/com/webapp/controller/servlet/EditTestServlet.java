package com.webapp.controller.servlet;

import com.webapp.database.test.TestDAO;
import com.webapp.entity.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//checked
@WebServlet("/editTest")
public class EditTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        TestDAO testDAO = TestDAO.getInstance();
        Test test = testDAO.getEntityById(id);
        testDAO.getQuestionsForTest(test);
        testDAO.getAnswersForQuestions(test);
        req.getSession().setAttribute("test",test);
        req.getSession().setAttribute("change","edit");
        req.getRequestDispatcher("/createtest.jsp")
                .forward(req,resp);
    }
}
