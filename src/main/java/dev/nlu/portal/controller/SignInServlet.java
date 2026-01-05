package dev.nlu.portal.controller;

import dev.nlu.portal.exception.BusinessException;
import dev.nlu.portal.model.User;
import dev.nlu.portal.service.UserService;
import dev.nlu.portal.utils.ForwardUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/sign-in"})
public class SignInServlet extends HttpServlet {
    UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/sign-in.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService = new UserService();
        String username = trim(req.getParameter("username"));
        String password = req.getParameter("password");

        try {
            User user = userService.login(username, password);
            req.getSession(true).setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/dashboard");
            System.out.println("User: " +   username + "Sign in success");
        } catch (BusinessException e) {
            ForwardUtils.forwardError(req, resp, "/WEB-INF/sign-in.jsp", e.getMessage());
            System.out.println("User: " +   username + "Sign in failed");
        }
    }

    private String trim(String value) {
        return (value == null || value.trim().isEmpty())
                ? null
                : value.trim();
    }
}
