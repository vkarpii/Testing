package com.webapp.controller.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * SessionLocaleListener add language attribute to new Session
 * and delete, when session was destroyed.
 */
@Slf4j
@WebListener
public class SessionLocaleListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info( SessionLocaleListener.class + " created");
        se.getSession().setAttribute("language","en");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info( SessionLocaleListener.class + " destroyed");
        se.getSession().removeAttribute("language");
    }
}
