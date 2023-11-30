package com.sosadwaden.filter;

import com.sosadwaden.dto.ReadUserDto;
import com.sosadwaden.entity.Role;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/admin")
public class UnsafeFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ReadUserDto user = (ReadUserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        if (user != null && user.getRole().equals(Role.ADMIN)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (user != null && !user.getRole().equals(Role.ADMIN)) {
            //((HttpServletResponse) servletResponse).sendRedirect("");
            // TODO придумать что делать, если пользователь есть, но он не админ и пытается зайти на админ страницу
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect("/login");
            // TODO тоже добавить какой-то текст что пользователь не админ
        }
    }
}
