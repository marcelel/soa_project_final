package com.agh.edu.client.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

public class LogFilter implements Filter {

    private Map<String, String> users;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        users = new HashMap<>();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Principal user = ((HttpServletRequest) servletRequest).getUserPrincipal();
        String sessionId = ((HttpServletRequest) servletRequest).getSession().getId();
        if (user == null){
            ((HttpServletResponse)servletResponse).sendRedirect("/login/login.xhtml");
            return;
        }
        String userName = user.getName();
        String userSessionId = users.get(userName);
        if (userSessionId == null) {
            users.put(userName, sessionId);
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (userSessionId.equals(sessionId)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletRequest)servletRequest).getSession().invalidate();
            String path = ((HttpServletRequest)servletRequest).getContextPath() + "/login/error.xhtml?logged=true";
            ((HttpServletResponse)servletResponse).sendRedirect(path);
        }
    }
}
