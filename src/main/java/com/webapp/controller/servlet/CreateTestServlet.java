package com.webapp.controller.servlet;

import com.webapp.database.subject.SubjectDAO;
import com.webapp.entity.Group;
import com.webapp.entity.Subject;
import com.webapp.entity.Test;
import com.webapp.database.group.GroupDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//checked
@WebServlet("/CreateTestServlet")
public class CreateTestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("tname");

        int time = Integer.parseInt(req.getParameter("ttime"));
        int attemps = Integer.parseInt(req.getParameter("tcount"));
        int complexity = Integer.parseInt(req.getParameter("tcomplexity"));
        int groupId = Integer.parseInt(req.getParameter("group"));
        int subjectId = Integer.parseInt(req.getParameter("subject"));

        Test test;

        if (req.getSession().getAttribute("test") == null)
            test = new Test();
        else
            test = (Test) req.getSession().getAttribute("test");

        test.setName(name);
        test.setMaxTime(time);
        test.setMaxAttemps(attemps);
        test.setComplexity(complexity);

        Subject subject = SubjectDAO.getInstance().getEntityById(subjectId);
        Group group = GroupDAO.getInstance().getEntityById(groupId);
        test.setGroup(group);
        test.setSubject(subject);

        req.getSession().setAttribute("test",test);


        resp.sendRedirect(req.getContextPath() + "/testsettings.jsp");
    }
}
