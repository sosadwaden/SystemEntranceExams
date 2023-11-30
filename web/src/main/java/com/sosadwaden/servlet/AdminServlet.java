package com.sosadwaden.servlet;

import com.sosadwaden.webUtil.JspPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.sosadwaden.webUtil.UrlPath.ADMIN;


@WebServlet(ADMIN)
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspPath.getJspPath("admin"))
                .forward(req, resp);
    }
}
