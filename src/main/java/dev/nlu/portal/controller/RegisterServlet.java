package dev.nlu.portal.controller;

import dev.nlu.portal.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    StudentService studentService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String email = req.getParameter("primaryEmail");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        if (!confirmPassword.equals(password)) {
            req.setAttribute("error", "Mật khẩu xác nhận không khớp");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }

        if (studentService.exists(id)) {
            req.setAttribute("error", "Username đã tồn tại");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
