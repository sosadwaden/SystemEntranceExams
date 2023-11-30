package com.sosadwaden.servlet;

import com.sosadwaden.service.FacultyService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.sosadwaden.webUtil.UrlPath.DELETE_FACULTY;
import static com.sosadwaden.webUtil.UrlPath.FACULTIES;


@WebServlet(DELETE_FACULTY)
public class DeleteFacultyServlet extends HttpServlet {

    private final FacultyService facultyService = FacultyService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long facultyId = Long.valueOf(req.getParameter("facultyId"));
        facultyService.delete(facultyId);
        resp.sendRedirect(FACULTIES);
    }
}
