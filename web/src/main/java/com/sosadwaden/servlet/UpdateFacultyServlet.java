package com.sosadwaden.servlet;

import com.sosadwaden.dto.UpdateFacultyDto;
import com.sosadwaden.service.FacultyService;
import com.sosadwaden.webUtil.JspPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.sosadwaden.webUtil.UrlPath.UPDATE_FACULTY;

@MultipartConfig
@WebServlet(UPDATE_FACULTY)
public class UpdateFacultyServlet extends HttpServlet {

    private final FacultyService facultyService = FacultyService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long facultyId = Long.valueOf(req.getParameter("facultyId"));
        req.setAttribute("faculty", facultyService.findById(facultyId));

        req.getRequestDispatcher(JspPath.getJspPath("update-faculty"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UpdateFacultyDto updateFacultyDto = UpdateFacultyDto.builder()
                .id(req.getParameter("facultyId"))
                .name(req.getParameter("name"))
                .facultyCapacity(req.getParameter("faculty_capacity"))
                .description(req.getParameter("description"))
                .image(req.getPart("image"))
                .build();
        facultyService.update(updateFacultyDto);
        resp.sendRedirect("/faculty?facultyId=" + req.getParameter("facultyId"));
    }
}
