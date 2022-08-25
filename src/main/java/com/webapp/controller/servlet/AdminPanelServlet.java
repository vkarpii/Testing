package com.webapp.controller.servlet;

import com.webapp.PaginationBuilder;
import com.webapp.database.group.GroupDAO;
import com.webapp.database.subject.SubjectDAO;
import com.webapp.database.user.UserDAO;
import com.webapp.entity.Group;
import com.webapp.entity.Subject;
import com.webapp.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

//checked
@WebServlet("/AdminPanelServlet")
public class AdminPanelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = UserDAO.getInstance();

        int noOfRecords = userDAO.getCount();
        PaginationBuilder paginationBuilder = new PaginationBuilder(req.getParameter("page"),noOfRecords);
        if (req.getParameter("changePages") != null){
            paginationBuilder.setCountOnPage(Integer.parseInt(req.getParameter("changePages")));
        }
        else if (req.getSession().getAttribute("countOnPage") != null){
            paginationBuilder.setCountOnPage((Integer) req.getSession().getAttribute("countOnPage"));
        }
        req.setAttribute("noOfPages", paginationBuilder.getNumberOfPages());
        req.setAttribute("currentPage", paginationBuilder.getPage());
        req.getSession().setAttribute("countOnPage",paginationBuilder.getCountOnPage());
        //System.out.println("PAGINATION OFFSET:"+(paginationBuilder.getPage() - 1)* paginationBuilder.getCountOnPage());
        ArrayList<User> users = userDAO.getLimit((paginationBuilder.getPage() - 1)* paginationBuilder.getCountOnPage(),
                paginationBuilder.getCountOnPage());

        ArrayList<Group> groups = GroupDAO.getInstance().getAll();
        req.getSession().setAttribute("groups",groups);

        ArrayList<Subject> subjects = SubjectDAO.getInstance().getAll();
        req.getSession().setAttribute("subjects",subjects);

        req.setAttribute("users",users);
        req.getRequestDispatcher("/adminpanel.jsp")
                .forward(req,resp);
    }
}
