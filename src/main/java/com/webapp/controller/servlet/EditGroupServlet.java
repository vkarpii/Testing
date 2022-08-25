package com.webapp.controller.servlet;

import com.webapp.database.group.GroupDAO;
import com.webapp.database.user.UserDAO;
import com.webapp.entity.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


//checked
@WebServlet("/editGroup")
public class EditGroupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUser = Integer.parseInt(req.getParameter("id"));
        ArrayList<Group> groups = UserDAO.getInstance().getGroupsByUserId(idUser);
        //System.out.println("arbait");
        req.setAttribute("editGroups",groups);
        req.getSession().setAttribute("idu",idUser);
        req.getRequestDispatcher("/editgroup.jsp")
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUser = (int) req.getSession().getAttribute("idu");
        GroupDAO groupDAO = GroupDAO.getInstance();
        req.getSession().removeAttribute("idu");
        if (req.getParameter("groupadd") != null){
            int group = Integer.parseInt(req.getParameter("groupadd"));
            //System.out.println(idUser + "/" + group);
            groupDAO.addUserToGroup(idUser,group);
        }
        if (req.getParameter("groupdelete")!=null){
            int group = Integer.parseInt(req.getParameter("groupdelete"));
            //System.out.println(idUser + "/" + group);
            groupDAO.deleteUserFromGroup(idUser,group);
        }
        resp.sendRedirect(req.getContextPath() + "/AdminPanelServlet");
    }
}
