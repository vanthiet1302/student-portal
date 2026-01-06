package dev.nlu.portal.controller;

import dev.nlu.portal.model.Role;
import dev.nlu.portal.model.User;
import dev.nlu.portal.service.UserService;
import dev.nlu.portal.utils.PasswordUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/sign-up"})
public class SignUpServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/sign-up.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = trim(req.getParameter("username"));
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String email = trim(req.getParameter("email"));
        String lastName = trim(req.getParameter("lastName"));
        String firstName = trim(req.getParameter("firstName"));
        String roleParam = req.getParameter("role");

        if (userService.existsByUsername(username)) {
            forwardError(req, resp, "Username đã tồn tại");
            return;
        }

        if (!password.equals(confirmPassword)) {
            forwardError(req, resp, "Mật khẩu không khớp");
            return;
        }

        if (userService.existsByEmail(email)) {
            forwardError(req, resp, "Email đã tồn tại");
            return;
        }

        if (firstName == null || lastName == null) {
            forwardError(req, resp, "Họ và tên là bắt buộc");
            return;
        }

        Role role;
        try {
            role = Role.valueOf(roleParam.toUpperCase());
        } catch (Exception e) {
            forwardError(req, resp, "Vui lòng chọn vai trò hợp lệ");
            return;
        }

        userService.create(User.builder()
                .username(username)
                .hashedPassword(PasswordUtils.hashpw(password))
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .role(role)
                .enabled(true)
                .build()
        );

        req.setAttribute("success", "Tạo tài khoản thành công");
        req.getRequestDispatcher("sign-in.jsp").forward(req, resp);
        System.out.println("User: " + username + " Sign up success");
    }

    private void forwardError(HttpServletRequest req, HttpServletResponse resp, String message)
            throws ServletException, IOException {
        req.setAttribute("error", message);
        req.getRequestDispatcher("/WEB-INF/sign-up.jsp").forward(req, resp);
    }

    private String trim(String value) {
        return (value == null || value.trim().isEmpty()) ? null : value.trim();
    }
}
