package com.webapp.controller.listener;



import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import static com.webapp.entity.Role.*;

/**
 * ContextRoleListener add roles to context
 */
@Slf4j
@WebListener
public class ContextRoleListener implements ServletContextListener {
    private ServletContext servletContext;
    private String attributeAdmin;
    private String attributeStudent;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.info( ContextRoleListener.class + " created");
        attributeAdmin ="Admin";
        attributeStudent = "Student";
        servletContext =servletContextEvent.getServletContext();
        servletContext.setAttribute(attributeAdmin, ADMIN);
        servletContext.setAttribute(attributeStudent, STUDENT);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.info( ContextRoleListener.class + " destroyed");
        servletContext.removeAttribute(attributeAdmin);
        servletContext.removeAttribute(attributeStudent);
    }
}
