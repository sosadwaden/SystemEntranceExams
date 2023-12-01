package com.sosadwaden.servlet;

import com.sosadwaden.dto.CreateFacultyDto;
import com.sosadwaden.exception.LoginException;
import com.sosadwaden.exception.ValidationException;
import com.sosadwaden.service.FacultyService;
import com.sosadwaden.webUtil.JspPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.sosadwaden.webUtil.UrlPath.CREATE_FACULTY;

@MultipartConfig
@WebServlet(CREATE_FACULTY)
public class CreateFacultyServlet extends HttpServlet {

    FacultyService facultyService = FacultyService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspPath.getJspPath("create-faculty"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateFacultyDto createFacultyDto = CreateFacultyDto.builder()
                .name(req.getParameter("name"))
                .facultyCapacity(req.getParameter("faculty_capacity"))
                .description(req.getParameter("description"))
                .image(req.getPart("image"))
                .build();

        try {
            facultyService.create(createFacultyDto);
            resp.sendRedirect("/faculties");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}
