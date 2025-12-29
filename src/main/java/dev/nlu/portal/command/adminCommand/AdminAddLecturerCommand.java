package dev.nlu.portal.command.adminCommand;

import dev.nlu.portal.command.Command;
import dev.nlu.portal.model.Lecturer;
import dev.nlu.portal.model.Role;
import dev.nlu.portal.model.User;
import dev.nlu.portal.service.LecturerService;
import dev.nlu.portal.service.UserService;
import dev.nlu.portal.utils.PasswordUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminAddLecturerCommand implements Command {
    private final UserService userService = new UserService();
    private final LecturerService lecturerService = new LecturerService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String method = request.getMethod();

        if ("GET".equalsIgnoreCase(method)) {
            return "/WEB-INF/views/admin/addLecturer.jsp";
        }

        try {
            String username = request.getParameter("username");
            User user = User.builder()
                    .username(username)
                    .role(Role.LECTURER)
                    .primaryEmail(request.getParameter("emailWork"))
                    .personEmail(request.getParameter("emailPersonal"))
                    .enabled(true)
                    .build();

            Lecturer lecturer = Lecturer.builder()
                    .userId(user.getId())
                    .user(user)
                    .fullName(request.getParameter("fullName"))
                    .birthYear(parseInteger(request.getParameter("birthYear")))
                    .gender(request.getParameter("gender"))
                    .identityCard(request.getParameter("identityCard"))
                    .academicRank(request.getParameter("academicRank"))
                    .degree(request.getParameter("degree"))
                    .specialization(request.getParameter("specialization"))
                    .position(request.getParameter("position"))
                    .department(request.getParameter("department"))
                    .workAgency(request.getParameter("workAgency"))
                    .agencyAddress(request.getParameter("agencyAddress"))
                    .phoneFixed(request.getParameter("phoneFixed"))
                    .fax(request.getParameter("fax"))
                    .emailWork(request.getParameter("emailWork"))
                    .emailPersonal(request.getParameter("emailPersonal"))
                    .phoneMobile(request.getParameter("phoneMobile"))
                    .bankAccountNumber(request.getParameter("bankAccountNumber"))
                    .bankName(request.getParameter("bankName"))
                    .bankBranch(request.getParameter("bankBranch"))
                    .build();

            boolean isSaved = lecturerService.insert(lecturer);

            if (isSaved) {
                return "redirect:/admin/lecturers?msg=success";
            } else {
                request.setAttribute("error", "Username đã tồn tại hoặc dữ liệu không hợp lệ.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi hệ thống: " + e.getMessage());
        }

        return "/WEB-INF/views/admin/addLecturer.jsp";
    }

    private Integer parseInteger(String value) {
        try {
            return (value != null && !value.isEmpty()) ? Integer.parseInt(value) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}