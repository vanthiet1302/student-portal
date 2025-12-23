package dev.nlu.portal.controller;

import dev.nlu.portal.model.Student;
import dev.nlu.portal.model.User;
import dev.nlu.portal.service.StudentServiceImpl;
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
    StudentServiceImpl studentService = new StudentServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userService.findByUsername(username);
        if (user != null) {
            if (PasswordUtil.checkPassword(password, user.getPasswordHash())) {
                req.getSession().setAttribute("user", user);
                String role = user.getRole().toString().toLowerCase();
                switch (role) {
                    case "admin":
                        resp.sendRedirect(req.getContextPath() + "/admin/home");
                        break;
                    case "student":
                        req.getSession().setAttribute("student", studentService.findById(user.getId()));
                        resp.sendRedirect(req.getContextPath() + "/student/home");
                        break;
                    case "teacher":
                        resp.sendRedirect(req.getContextPath() + "/teacher/home");
                        break;
                    default:
                        req.setAttribute("error", "Vai trò người dùng không hợp lệ!");
                        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/auth/login.jsp");
                        rd.forward(req, resp);
                        break;
                }
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
