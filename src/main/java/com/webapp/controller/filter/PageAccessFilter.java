package com.webapp.controller.filter;

import com.webapp.entity.Role;
import com.webapp.entity.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

import static com.webapp.entity.Role.*;

@Slf4j
@WebFilter(urlPatterns = "/*",
        initParams = {@WebInitParam(name = "student",value = "InfoServlet LogoutServlet SaveResult SearchServlet SortingServlet test changepass"),
            @WebInitParam(name = "admin",value = "AddGroupServlet AddSubjectServlet AdminPanelServlet block CreateQuestionServlet CreateTestServlet DeleteGroupServlet DeleteQuestionServlet DeleteSubjectServlet delete deleteUser editTest editUserByAdmin PublishTestServlet role SaveEditedByAdmServlet seeResults")})
public class PageAccessFilter implements Filter {
    private Map<Role,List<String>> accessMap;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        accessMap = new HashMap<>();
        accessMap.put(STUDENT, asList(filterConfig.getInitParameter(STUDENT.getName())));
        accessMap.put(ADMIN, asList(filterConfig.getInitParameter(ADMIN.getName())));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (accessAllowed(servletRequest)){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            User user = (User) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
            log.warn("User : {} /login : {} tried to access a resource forbidden to him.",user.getName(),user.getLogin());
            servletRequest.getRequestDispatcher("/error.jsp")
                    .forward(servletRequest,servletResponse);
        }
    }
    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String[] urlPart = httpRequest.getRequestURI().split("/");
        String servletName = urlPart[urlPart.length-1];
        if (servletName.isEmpty()){
            return true;
        }
        User user = (User) httpRequest.getSession().getAttribute("user");
        if (user == null){
            return !(accessMap.get(ADMIN).contains(servletName) || accessMap.get(STUDENT).contains(servletName));
        }
        Role role =  user.getRole();
        if (!accessMap.get(STUDENT).contains(servletName) && !accessMap.get(ADMIN).contains(servletName))
            return true;
        if (accessMap.get(STUDENT).contains(servletName) && role == STUDENT){
            return true;
        }
        return role == ADMIN;
    }
    private List<String> asList(String str) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) list.add(st.nextToken());
        return list;
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
