package com.webapp.controller.servlet;

import com.webapp.database.subject.SubjectDAO;
import com.webapp.database.test.TestDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//checked
@WebServlet("/DeleteSubjectServlet")
public class DeleteSubjectServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("subjectdelete");
        TestDAO.getInstance().deleteAllForSubject(name);
        SubjectDAO.getInstance().deleteByName(name);
        //req.getRequestDispatcher("/AdminPanelServlet?page=1");
        resp.sendRedirect(req.getContextPath() + "/AdminPanelServlet?page=1");
    }
}
