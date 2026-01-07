package dev.nlu.portal.controller.command.admin;

import dev.nlu.portal.controller.command.Command;
import dev.nlu.portal.model.Role;
import dev.nlu.portal.model.Status;
import dev.nlu.portal.model.Student;
import dev.nlu.portal.model.User;
import dev.nlu.portal.service.StudentService;
import dev.nlu.portal.service.UserService;
import dev.nlu.portal.utils.PasswordUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class AddStudentCommand implements Command {
    UserService userService = new UserService();
    StudentService studentService = new StudentService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        if ("GET".equals(req.getMethod())) {
            req.setAttribute("content", "/WEB-INF/views/pages/admin/student/add.jsp");
            req.getRequestDispatcher("/WEB-INF/views/layout/admin/main.jsp").forward(req, res);
        }

        String username = trim(req.getParameter("username"));
        String password = req.getParameter("password");
        String email = trim(req.getParameter("email"));
        String lastName = trim(req.getParameter("lastName"));
        String firstName = trim(req.getParameter("firstName"));
        String roleParam = req.getParameter("role");

        if (userService.existsByUsername(username)) {
            return forwardError(req, res, "Username đã tồn tại");
        }

        if (userService.existsByEmail(email)) {
            return forwardError(req, res, "Email đã tồn tại");
        }

        if (firstName == null || lastName == null) {
            return forwardError(req, res, "Họ và tên là bắt buộc");
        }

        Role role;
        try {
            role = Role.valueOf(roleParam.toUpperCase());
        } catch (Exception e) {
            return forwardError(req, res, "Vui lòng chọn vai trò hợp lệ");
        }

        User user = User.builder()
                .username(username)
                .hashedPassword(PasswordUtils.hashpw(password))
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .role(role)
                .enabled(true)
                .build();

        userService.create(user);

        LocalDate dob = LocalDate.parse(req.getParameter("dob"));
        Status status = Status.STUDYING;
        String classId = req.getParameter("classId");

        Student student = Student.builder()
                .user(user)
                .userId(user.getId())
                .dob(dob)
                .status(status)
                .classId(classId)
                .build();

        studentService.create(student);
        return "redirect:/admin/students";
    }

    private String forwardError(HttpServletRequest req, HttpServletResponse resp, String message)
            throws ServletException, IOException {
        req.setAttribute("error", message);
        return "/WEB-INF/views/pages/sign-up.jsp";
    }

    private String trim(String value) {
        return (value == null || value.trim().isEmpty()) ? null : value.trim();
    }
}
