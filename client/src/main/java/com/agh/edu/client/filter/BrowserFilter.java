package com.agh.edu.client.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BrowserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String userAgent = ((HttpServletRequest) servletRequest).getHeader("User-Agent");
        if (userAgent.contains("Edge")) {
            ((HttpServletRequest) servletRequest).getSession().invalidate();
            ((HttpServletResponse) servletResponse).sendRedirect(((HttpServletRequest)servletRequest).getContextPath()
                    + "/login/error.xhtml?wrongBrowser=true");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
