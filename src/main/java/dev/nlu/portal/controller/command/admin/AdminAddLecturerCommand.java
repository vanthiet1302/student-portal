package dev.nlu.portal.controller.command.admin;

import dev.nlu.portal.controller.command.Command;
import dev.nlu.portal.exception.ServiceException;
import dev.nlu.portal.model.Lecturer;
import dev.nlu.portal.model.User;
import dev.nlu.portal.service.LecturerService;
import dev.nlu.portal.service.UserService;
import dev.nlu.portal.utils.PasswordUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminAddLecturerCommand implements Command {

    private final LecturerService lecturerService = new LecturerService();
    private final UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        if ("GET".equalsIgnoreCase(request.getMethod())) {
            return showForm(request);
        }

        try {
            String username = trim(request.getParameter("username"));
            String email = trim(request.getParameter("emailWork"));
            String firstName = trim(request.getParameter("firstName"));
            String lastName = trim(request.getParameter("lastName"));
            String department = trim(request.getParameter("department"));

            if (username == null) return error(request, "Username là bắt buộc.");
            if (email == null) return error(request, "Email là bắt buộc.");
            if (firstName == null) return error(request, "Tên là bắt buộc.");
            if (lastName == null) return error(request, "Họ là bắt buộc.");
            if (department == null) return error(request, "Khoa/Bộ môn là bắt buộc.");
            
            try {
                userService.getByUsername(username);
                return error(request, "Username đã tồn tại.");
            } catch (ServiceException ignored) {}

            try {
                userService.getByEmail(email);
                return error(request, "Email đã được sử dụng.");
            } catch (ServiceException ignored) {}

            User user = User.builder()
                .username(username)
                .hashedPassword(PasswordUtils.hashpw("123456"))
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .enabled(true)
                .build();

            Lecturer lecturer = Lecturer.builder()
                .user(user)
                .birthYear(parseInteger(request.getParameter("birthYear")))
                .gender(trim(request.getParameter("gender")))
                .identityCard(trim(request.getParameter("identityCard")))
                .academicRank(trim(request.getParameter("academicRank")))
                .degree(trim(request.getParameter("degree")))
                .specialization(trim(request.getParameter("specialization")))
                .position(trim(request.getParameter("position")))
                .department(department)
                .workAgency(trim(request.getParameter("workAgency")))
                .agencyAddress(trim(request.getParameter("agencyAddress")))
                .phoneFixed(trim(request.getParameter("phoneFixed")))
                .fax(trim(request.getParameter("fax")))
                .phoneMobile(trim(request.getParameter("phoneMobile")))
                .bankAccountNumber(trim(request.getParameter("bankAccountNumber")))
                .bankName(trim(request.getParameter("bankName")))
                .bankBranch(trim(request.getParameter("bankBranch")))
                .build();

            lecturerService.create(lecturer);

            return "redirect:/admin/lecturers?msg=success";

        } catch (ServiceException e) {
            return error(request, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // Log the error to console
            return error(request, "Lỗi hệ thống: " + e.getMessage());
        }
    }

    private String showForm(HttpServletRequest request) {
        request.setAttribute("contentPage", "/WEB-INF/views/pages/admin/lecturer/add.jsp");
        request.setAttribute("pageTitle", "Add Lecturer");
        return "/WEB-INF/views/layout/layout.jsp";
    }

    private String error(HttpServletRequest request, String message) {
        request.setAttribute("error", message);
        return showForm(request);
    }

    private String trim(String value) {
        return (value == null || value.trim().isEmpty()) ? null : value.trim();
    }

    private Integer parseInteger(String value) {
        try {
            return (value != null && !value.isEmpty())
                    ? Integer.parseInt(value)
                    : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
