package com.webapp.controller.servlet;

import com.webapp.database.test.TestDAO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet("/delete")
public class DeleteTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idTest = Integer.parseInt(req.getParameter("id"));
        TestDAO testDAO = TestDAO.getInstance();
        testDAO.delete(idTest);
        log.info("Delete test id = {}",idTest);
        req.getRequestDispatcher("/main?page=1")
                        .forward(req,resp);
    }
}
