package dev.nlu.portal.controller.command;

import dev.nlu.portal.model.Lecturer;
import dev.nlu.portal.model.Role;
import dev.nlu.portal.model.User;
import dev.nlu.portal.service.LecturerServiceImpl;
import dev.nlu.portal.service.UserServiceImpl;
import dev.nlu.portal.utils.PasswordUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;

public class AdminAddLecturerController implements Command {
    UserServiceImpl userService = new UserServiceImpl();
    LecturerServiceImpl lecturerService = new LecturerServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String method = request.getMethod();
        if ("GET".equalsIgnoreCase(method)) {
            return "/WEB-INF/views/admin/addLecturer.jsp";
        }

        // 1. Khởi tạo User với mật khẩu mặc định (birthYear + birthYear + @)
        int birthYear = Integer.parseInt(request.getParameter("birthYear"));
        String passwordDefault = PasswordUtil.hashPassword(birthYear + "" + birthYear + "@");

        User user = User.builder()
                .username(request.getParameter("username"))
                .passwordHashed(passwordDefault)
                .role(Role.LECTURER)
                .build();

        // 2. Map dữ liệu vào object Lecturer
        Lecturer lecturer = Lecturer.builder()
                .user(user)
                .fullName(request.getParameter("fullName"))
                .birthYear(birthYear)
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

        // 3. Lưu vào Database thông qua Service
        boolean savedLecturer = lecturerService.create(lecturer);

        if (savedLecturer) {
            // 4. THÀNH CÔNG: Redirect sang trang detail bằng phương thức GET
            return "redirect:/admin/detailLecturer?id=" + lecturer.getId();
        } else {
            request.setAttribute("error", "Lỗi: Không thể tạo giảng viên hoặc Username đã tồn tại.");
            return "/WEB-INF/views/admin/addLecturer.jsp";
        }
    }
}
