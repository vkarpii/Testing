package com.webapp.controller.servlet;

import com.webapp.entity.Answer;
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
@WebServlet("/CreateQuestionServlet")
public class CreateQuestionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Test test = (Test) req.getSession().getAttribute("test");
        ArrayList<Question> question = test.getQuestions();
        Question newQuestion = new Question();
        System.out.println("CreateQuestionServlet#doPost");
        String text = req.getParameter("textarea");
        newQuestion.setText(text);
        ArrayList<Answer> answers = new ArrayList<>();
        for (int i = 1; i <= 5;i++){
            System.out.println(req.getParameter("textarea" + i));
            if(req.getParameter("textarea" + i) != null){
                Answer answer = new Answer();
                String textarea = req.getParameter("textarea" + i);
                answer.setText(textarea);
                boolean point = Boolean.parseBoolean(req.getParameter("acorrect" + i));
                answer.setCorrect(point);
                answers.add(answer);
                System.out.println(answer);
            }
        }
        //перевірка чи є хоч 1 true -> answers
        boolean check = false;
        for (int i = 0; i!=answers.size();i++)
            if (answers.get(i).getCorrect()){
                check = true;
                break;
            }
        if (check){
            newQuestion.setAnswers(answers);
            System.out.println(newQuestion);
            question.add(newQuestion);
            resp.sendRedirect(req.getContextPath() + "/testsettings.jsp");
        } else
            resp.sendRedirect(req.getContextPath() + "/testsettings.jsp");
    }
}
