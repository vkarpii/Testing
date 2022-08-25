package com.webapp.controller.servlet;

import com.webapp.entity.Answer;
import com.webapp.entity.Question;
import com.webapp.entity.Test;
import com.webapp.database.test.TestDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/PublishTestServlet")
public class PublishTestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Test test = (Test) req.getSession().getAttribute("test");
        TestDAO testDAO = TestDAO.getInstance();

        String change = (String) req.getSession().getAttribute("change");
        req.getSession().removeAttribute("change");
        int oldTestId = test.getId();
        String locale = (String) req.getSession().getAttribute("language");
        test.setLocaleIdByString(locale);
        testDAO.create(test);
        ArrayList<Question> questions = test.getQuestions();
        int corrAns = 0;
        for (int i = 0; i!= questions.size();i++){
            ArrayList<Answer> answers = questions.get(i).getAnswers();
            for (int j =0;j!=answers.size();j++)
                if (answers.get(j).getCorrect())
                    corrAns++;
            questions.get(i).setCheckbox(corrAns > 1);
            corrAns = 0;
            questions.get(i).setAnswers(answers);
        }
        test.setQuestions(questions);
        testDAO.addQuestions(test);
        testDAO.addTestQuestion(test);
        testDAO.addAnswers(test);
        testDAO.addQuestionAnswer(test);
        testDAO.addTestGroup(test);
        req.getSession().removeAttribute("test");
        if (change != null && change.equals("edit")){
            //створення нового тесту
            testDAO.delete(oldTestId);
        }
        resp.sendRedirect(req.getContextPath() + "/main");

    }
}
