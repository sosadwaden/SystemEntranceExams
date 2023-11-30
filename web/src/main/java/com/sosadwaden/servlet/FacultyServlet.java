package com.sosadwaden.servlet;


import com.sosadwaden.dto.ReadUserDto;
import com.sosadwaden.service.FacultyService;
import com.sosadwaden.service.UserService;
import com.sosadwaden.webUtil.JspPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.sosadwaden.webUtil.UrlPath.FACULTY;

@WebServlet(FACULTY)
public class FacultyServlet extends HttpServlet {

    FacultyService facultyService = FacultyService.getInstance();
    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long facultyId = Long.valueOf(req.getParameter("facultyId"));
        req.setAttribute("faculty", facultyService.findById(facultyId));

        req.getRequestDispatcher(JspPath.getJspPath("faculty"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long facultyId = Long.valueOf(req.getParameter("facultyId"));
        Long userId = ((ReadUserDto) req.getSession().getAttribute("user")).getId();
        userService.update(userId, facultyId);
        doGet(req, resp);
    }
}
