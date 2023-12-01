package com.sosadwaden.servlet;

import com.sosadwaden.service.TeacherService;
import com.sosadwaden.service.UserService;
import com.sosadwaden.webUtil.JspPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.sosadwaden.webUtil.UrlPath.APPLICANT;

@WebServlet(APPLICANT)
public class ApplicantServlet extends HttpServlet {

    private final UserService applicantService = UserService.getInstance();
    private final TeacherService teacherService = TeacherService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long facultyId = Long.valueOf(req.getParameter("facultyId"));
        req.setAttribute("applicants", applicantService.findByFacultyAndSortByScore(facultyId));
        req.setAttribute("facultyId", facultyId);

        req.getRequestDispatcher(JspPath.getJspPath("applicant"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long facultyId = Long.valueOf(req.getParameter("facultyId"));
        teacherService.generateExamResults(facultyId);
        doGet(req, resp);
    }
}
