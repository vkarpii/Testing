package com.webapp.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class TestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String[] urlPart = httpRequest.getRequestURI().split("/");
        String servletName = urlPart[urlPart.length-1];
        if (httpRequest.getSession().getAttribute("reload") != null &&
                !servletName.equals("test.jsp")  && !servletName.equals("SaveResult")){
                servletRequest.getRequestDispatcher("/test.jsp")
                        .forward(servletRequest,servletResponse);
                return;
            }
        filterChain.doFilter(servletRequest,servletResponse);
    }

}
