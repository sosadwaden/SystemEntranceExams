package com.sosadwaden.servlet;

import com.sosadwaden.dto.ReadUserDto;
import com.sosadwaden.service.UserService;
import com.sosadwaden.webUtil.JspPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.sosadwaden.webUtil.UrlPath.LOGIN;

@WebServlet(LOGIN)
public class LoginServlet extends HttpServlet {

    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspPath.getJspPath("login"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        userService.login(req.getParameter("email"), req.getParameter("password"))
                .ifPresentOrElse(
                        user -> onLoginSuccess(user, req, resp),
                        () -> onLoginFail(req, resp)
                );
    }

    private void onLoginSuccess(ReadUserDto user, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("user", user);
        try {
            resp.sendRedirect("/faculties");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.sendRedirect("/login?error&email=" + req.getParameter("email") + "&password=" + req.getParameter("password"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
