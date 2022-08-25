package com.webapp.controller.servlet;

import com.webapp.entity.Question;
import com.webapp.entity.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


//checked
@WebServlet("/DeleteQuestionServlet")
public class DeleteQuestionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Test test = (Test) req.getSession().getAttribute("test");
        ArrayList<Question> questions = test.getQuestions();
        questions.remove(id);
        req.getRequestDispatcher("/testsettings.jsp")
                .forward(req,resp);
    }
}
