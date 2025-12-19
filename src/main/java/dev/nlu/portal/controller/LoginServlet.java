package dev.nlu.portal.controller;

import dev.nlu.portal.model.User;
import dev.nlu.portal.service.UserServiceImpl;
import dev.nlu.portal.utils.PasswordUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userService.findByUsername(username);
        if (user != null) {
            if (PasswordUtil.checkPassword(password, user.getPasswordHash())) {
                req.getSession().setAttribute("user", user);
                resp.sendRedirect(req.getContextPath() + "/home");
            } else {
                req.setAttribute("error", "Mã sinh viên hoặc mật khẩu không đúng!");
                RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/auth/login.jsp");
                rd.forward(req, resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/auth/login.jsp");
        rd.forward(req, resp);
    }
}
