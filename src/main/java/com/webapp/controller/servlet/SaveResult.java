package com.webapp.controller.servlet;

import com.webapp.entity.Answer;
import com.webapp.entity.Question;
import com.webapp.entity.Test;
import com.webapp.entity.User;
import com.webapp.database.user.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet("/SaveResult")
public class SaveResult extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("reload");
        //System.out.println("SaveResult#doGET");
        Test test = (Test) req.getSession().getAttribute("Test");
        ArrayList<Question> questions = test.getQuestions();
        ArrayList<ArrayList<String>> correctAnswers = new ArrayList<>();
        for (int i = 0;i!= questions.size();i++){
            ArrayList<Answer> answers = questions.get(i).getAnswers();
            ArrayList<String> res = new ArrayList<>();
            for (int j=0;j!=answers.size();j++){
                if (answers.get(j).getCorrect()){
                    res.add(answers.get(j).getText());
                }
            }
            correctAnswers.add(res);
        }
        //System.out.println("----------");
        ArrayList<ArrayList<String>> userAnswers = new ArrayList<>();
        //System.out.println(Arrays.toString(correctAnswers.toArray()));
        for (int i = 0;i!=questions.size();i++){
            if (questions.get(i).isCheckbox()){
                ArrayList<Answer> answers = questions.get(i).getAnswers();
                ArrayList<String> res = new ArrayList<>();
                //System.out.println(answers.size());
                for (int j = 0;j != answers.size();j++){
                    String r = req.getParameter("question"+questions.get(i).getId()+answers.get(j).getId());
                    if (r != null){
                        res.add(r);
                    }
                }
                userAnswers.add(res);
                //System.out.println(Arrays.toString(res.toArray()));
            } else {
                ArrayList<String> result = new ArrayList<>();
                result.add(req.getParameter("question"+questions.get(i).getId()));
                userAnswers.add(result);
                //System.out.println(result);
            }
        }

        double result = 0;
        int corrects = 0;
        for (int i=0;i!=correctAnswers.size();i++){
            if (correctAnswers.get(i).equals(userAnswers.get(i)))
                corrects++;
        }
        result = ((double) corrects  / correctAnswers.size()) * 100.0;
        req.getSession().setAttribute("CurrResult", result);
        //System.out.println("You result : "+result+"%");

        UserDAO userDAO = UserDAO.getInstance();
        User user = (User) req.getSession().getAttribute("user");
        if (!userDAO.checkResult(test, user)){
            //true -> не існує
            userDAO.addResult(test, user,result);
        } else {
            int attemps = userDAO.getAttemps(test,user) + 1;//get attemps
            double curResult = userDAO.getCurrResult(test,user);//get result
            if (curResult > result)
                result = curResult;
            userDAO.updateResult(test,user,result,attemps);
        }
        req.getSession().removeAttribute("Test");
        req.getSession().setAttribute("testName",test.getName());
        /*req.getRequestDispatcher("/result.jsp")
                .forward(req,resp);*/
        resp.sendRedirect(req.getContextPath() + "/result.jsp");
    }
}
