package com.sosadwaden.servlet;

import com.sosadwaden.dto.CreateUserDto;
import com.sosadwaden.entity.Role;
import com.sosadwaden.entity.Status;
import com.sosadwaden.exception.LoginException;
import com.sosadwaden.exception.ValidationException;
import com.sosadwaden.service.UserService;
import com.sosadwaden.webUtil.JspPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.sosadwaden.webUtil.UrlPath.REGISTRATION;

@WebServlet(REGISTRATION)
public class RegistrationServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", Role.values());

        req.getRequestDispatcher(JspPath.getJspPath("registration"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateUserDto userDto = CreateUserDto.builder()
                .name(req.getParameter("name"))
                .surname(req.getParameter("surname"))
                .birthday(req.getParameter("birthday"))
                .status(Status.DEFAULT.name())
                .role(req.getParameter("role"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .build();

        try {
            userService.create(userDto);
            resp.sendRedirect("/login");
        } catch (LoginException exception) {
            req.setAttribute("login_error", exception.getError());
            doGet(req, resp);
        }catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }

    }
}
