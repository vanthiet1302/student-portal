package dev.nlu.portal.controller;

import java.io.IOException;

import dev.nlu.portal.model.Student;
import dev.nlu.portal.model.User;
import dev.nlu.portal.service.IService;
import dev.nlu.portal.service.StudentServiceImpl;
import dev.nlu.portal.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
    IService<User> service = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.parseLong(req.getParameter("userId"));
        String password = req.getParameter("password");

        User user = service.login(userId, password);
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            resp.sendRedirect(req.getContextPath() + "/student/profile");
        } else {
            req.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
            forwardToLogin(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardToLogin(req, resp);
    }

    private void forwardToLogin(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/auth/login.jsp").forward(req, resp);
    }
}
