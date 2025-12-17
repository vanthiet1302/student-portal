package dev.nlu.portal.controller;

import dev.nlu.portal.model.Student;
import dev.nlu.portal.service.StudentService;
import dev.nlu.portal.utils.PasswordUtil;
import dev.nlu.portal.utils.UUIDUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    StudentService studentService = new StudentService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("primaryEmail");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");

        if (!password.equals(confirmPassword)) {
            req.setAttribute("error", "Mật khẩu xác nhận không khớp");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }

        if (studentService.existsByUsername(username)) {
            req.setAttribute("error", "Mã số sinh viên đã tồn tại, chuyển sang trang đăng nhập");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }

        Student student = new Student();
        student.setId(UUIDUtil.generateUUID());
        student.setUsername(username);
        student.setPrimaryEmail(email);
        student.setHashPassword(PasswordUtil.hashPassword(password));
        student.setFirstname(firstname);
        student.setLastname(lastname);
        studentService.save(student);
        resp.sendRedirect("login.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
