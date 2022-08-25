package com.webapp.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class SessionLocaleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getParameter("sessionLocale") != null)
            request.getSession().setAttribute("language",request.getParameter("sessionLocale"));
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
