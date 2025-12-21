package dev.nlu.portal.controller;

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
import java.time.Instant;

public class ResetPasswordServlet extends HttpServlet {
    private final StudentServiceImpl studentService = new StudentServiceImpl();
    private final UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/auth/verify-code.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String codeInput = req.getParameter("code");
        String newPassword = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        String email = (String) req.getSession().getAttribute("fp_email");
        String code = (String) req.getSession().getAttribute("fp_code");
        Long expires = (Long) req.getSession().getAttribute("fp_expires");

        if (email == null || code == null || expires == null) {
            req.setAttribute("error", "Phiên xác thực không hợp lệ hoặc đã hết hạn.");
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/auth/verify-code.jsp");
            rd.forward(req, resp);
            return;
        }

        if (Instant.now().toEpochMilli() > expires) {
            clearSession(req);
            req.setAttribute("error", "Mã xác thực đã hết hạn. Vui lòng yêu cầu lại.");
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/auth/forgot-password.jsp");
            rd.forward(req, resp);
            return;
        }

        if (!code.equals(codeInput)) {
            req.setAttribute("error", "Mã xác thực không đúng.");
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/auth/verify-code.jsp");
            rd.forward(req, resp);
            return;
        }

        if (newPassword == null || newPassword.isBlank() || !newPassword.equals(confirmPassword)) {
            req.setAttribute("error", "Mật khẩu không hợp lệ hoặc không khớp.");
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/auth/verify-code.jsp");
            rd.forward(req, resp);
            return;
        }

        var student = studentService.findByEmail(email);
        if (student == null) {
            req.setAttribute("error", "Không tìm thấy sinh viên với email này.");
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/auth/verify-code.jsp");
            rd.forward(req, resp);
            return;
        }

        Long userId = student.getUserId();
        User user = userService.findById(userId);
        if (user == null) {
            req.setAttribute("error", "Không tìm thấy tài khoản người dùng.");
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/auth/verify-code.jsp");
            rd.forward(req, resp);
            return;
        }

        user.setPasswordHash(PasswordUtil.hashPassword(newPassword));
        userService.update(user);

        clearSession(req);
        req.setAttribute("message", "Đặt lại mật khẩu thành công. Vui lòng đăng nhập.");
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/auth/login.jsp");
        rd.forward(req, resp);
    }

    private void clearSession(HttpServletRequest req) {
        req.getSession().removeAttribute("fp_email");
        req.getSession().removeAttribute("fp_code");
        req.getSession().removeAttribute("fp_expires");
    }
}