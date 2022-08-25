package com.webapp.controller.servlet;

import com.webapp.database.subject.SubjectDAO;
import com.webapp.entity.Subject;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet("/AddSubjectServlet")
public class AddSubjectServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("subject");
        Subject newSubject = new Subject();
        newSubject.setName(name);
        if (!SubjectDAO.getInstance().checkName(newSubject))
            SubjectDAO.getInstance().create(newSubject);
        log.info("Create subject - {}",newSubject.getName());
        /*req.getRequestDispatcher("/AdminPanelServlet?page=1")
                .forward(req,resp);*/
        resp.sendRedirect(req.getContextPath() +"/AdminPanelServlet?page=1");
    }

}
