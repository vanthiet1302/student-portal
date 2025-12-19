package dev.nlu.portal.controller;

import dev.nlu.portal.model.*;
import dev.nlu.portal.service.StudentServiceImpl;
import dev.nlu.portal.service.UserServiceImpl;
import dev.nlu.portal.utils.PasswordUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class RegisterServlet extends HttpServlet {

    private UserServiceImpl userService = new UserServiceImpl();
    private StudentServiceImpl studentService = new StudentServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String studentCode = req.getParameter("studentCode");
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String dateOfBirthStr = req.getParameter("dateOfBirth");
        String genderStr = req.getParameter("gender");
        String address = req.getParameter("address");
        String major = req.getParameter("major");
        String className = req.getParameter("className");

        if (userService.findByUsername(username) != null) {
            req.setAttribute("error", "Tên đăng nhập đã tồn tại!");
            forwardToRegister(req, resp);
            return;
        }

        if (!password.equals(confirmPassword)) {
            req.setAttribute("error", "Mật khẩu xác nhận không khớp!");
            forwardToRegister(req, resp);
            return;
        }

        String passwordHash = PasswordUtil.hashPassword(password);

        User newUser = User.builder()
                .username(username)
                .passwordHash(passwordHash)
                .role(Role.STUDENT)
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        userService.save(newUser);

        Long userId = newUser.getId();

        Student student = Student.builder()
                .userId(userId)
                .studentCode(studentCode)
                .fullName(fullName)
                .email(email)
                .phone(phone)
                .dateOfBirth(LocalDate.parse(dateOfBirthStr))
                .gender(genderStr != null ? Gender.valueOf(genderStr) : null)
                .address(address)
                .major(major)
                .className(className)
                .build();
        studentService.save(student);

        req.getSession().setAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
        req.getRequestDispatcher("/login").forward(req, resp);
    }

    private void forwardToRegister(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/auth/register.jsp");
        rd.forward(req, resp);
    }
}