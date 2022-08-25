package com.webapp.controller.servlet;

import com.webapp.database.group.GroupDAO;
import com.webapp.database.test.TestDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//checked
@WebServlet("/DeleteGroupServlet")
public class DeleteGroupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("groupdelete");

        //видалити всі тести які прикріплені до цієї групи
        TestDAO.getInstance().deleteAllForGroup(name);
        GroupDAO.getInstance().deleteByName(name);
        /*req.getRequestDispatcher("/AdminPanelServlet?page=1")
                        .forward(req,resp);*/
        resp.sendRedirect(req.getContextPath() + "/AdminPanelServlet?page=1");
    }
}
