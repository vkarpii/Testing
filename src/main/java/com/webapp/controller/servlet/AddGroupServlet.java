package com.webapp.controller.servlet;


import com.webapp.entity.Group;
import com.webapp.database.group.GroupDAO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet("/AddGroupServlet")
public class AddGroupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("group");
        Group newGroup = new Group();
        newGroup.setName(name);
        if (!GroupDAO.getInstance().checkName(newGroup))
            GroupDAO.getInstance().create(newGroup);
        log.info("Create group - {}",newGroup.getName());
        /*req.getRequestDispatcher("/AdminPanelServlet?page=1")
                        .forward(req,resp);*/
        resp.sendRedirect(req.getContextPath()  +"/AdminPanelServlet?page=1");
    }
}
