package com.sosadwaden.servlet;

import com.sosadwaden.service.FacultyService;
import com.sosadwaden.webUtil.JspPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.sosadwaden.webUtil.UrlPath.FACULTIES;


@WebServlet(FACULTIES)
public class FacultiesServlet extends HttpServlet {

    private final FacultyService facultyService = FacultyService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("faculties", facultyService.findAll());

        req.getRequestDispatcher(JspPath.getJspPath("faculties"))
                .forward(req, resp);
    }
}
