package dev.nlu.portal.controller.command.admin;

import dev.nlu.portal.exception.ServiceException;
import dev.nlu.portal.model.Student;
import dev.nlu.portal.model.User;
import dev.nlu.portal.service.StudentService;
import dev.nlu.portal.service.UserService;
import dev.nlu.portal.utils.PasswordUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = {"/admin/students/*"})
public class StudentServlet extends HttpServlet {
    private static final String VIEW_LAYOUT = "/WEB-INF/views/layout/admin/main.jsp";
    private static final String VIEW_LIST = "/WEB-INF/views/pages/admin/student/list.jsp";
    private static final String VIEW_ADD = "/WEB-INF/views/pages/admin/student/add.jsp";
    private static final String VIEW_EDIT = "/WEB-INF/views/pages/admin/student/edit.jsp";

    private final StudentService studentService = new StudentService();
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo == null || "/".equals(pathInfo)) {
            list(req, resp);
            return;
        }

        switch (pathInfo) {
            case "/add" -> {
                req.setAttribute("content", VIEW_ADD);
                req.setAttribute("title", "admin.title.student.add");
                req.getRequestDispatcher(VIEW_LAYOUT).forward(req, resp);
            }
            case "/edit" -> showEditForm(req, resp);
            default -> resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        try {
            if (pathInfo == null || "/".equals(pathInfo)) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            switch (pathInfo) {
                case "/add" -> handleAddStudent(req, resp);
                case "/edit" -> handleEditStudent(req, resp);
                case "/delete" -> handleDeleteStudent(req, resp);
                default -> resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (ServiceException e) {
            // Fallback error handling
            req.setAttribute("error", e.getMessage());
            req.setAttribute("content", VIEW_LIST);
            req.getRequestDispatcher(VIEW_LAYOUT).forward(req, resp);
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> lst = studentService.getAll();

        req.setAttribute("students", lst);

        req.setAttribute("content", VIEW_LIST);
        req.setAttribute("title", "admin.title.student.list");
        req.getRequestDispatcher(VIEW_LAYOUT).forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");

        if (userId == null || userId.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/admin/students");
            return;
        }

        try {
            Student student = studentService.getById(userId);
            req.setAttribute("student", student);

            req.setAttribute("content", VIEW_EDIT);
            req.setAttribute("title", "admin.title.student.edit");
            req.getRequestDispatcher(VIEW_LAYOUT).forward(req, resp);
        } catch (ServiceException e) {
            // Không tìm thấy student -> Redirect về list kèm thông báo lỗi (nếu cần dùng session flash attributes)
            resp.sendRedirect(req.getContextPath() + "/admin/students");
        }
    }

    private void handleAddStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Lấy dữ liệu từ form
        String username = trim(req.getParameter("username"));
//        String password = req.getParameter("password");
//        String confirmPassword = req.getParameter("confirmPassword");
//        String email = trim(req.getParameter("email"));
        String email = username + "@st.hcmuaf.edu.vn";
        String firstName = trim(req.getParameter("firstName"));
        String lastName = trim(req.getParameter("lastName"));

        keepFormValues(req);

        // 3. Validation
        if (username.isEmpty()) {
            setErrorAndForward(req, resp, "admin.student.add.error.usernameRequired", VIEW_ADD);
            return;
        }

        if (userService.existsByUsername(username)) {
            setErrorAndForward(req, resp, "admin.student.add.error.usernameTaken", VIEW_ADD);
            return;
        }

        try {
            User user = User.builder()
                    .username(username)
                    .hashedPassword(PasswordUtils.hashpw(username))
                    .email(email)
                    .firstName(firstName)
                    .lastName(lastName)
                    .enabled(true)
                    .build();

            Student student = Student.builder()
                    .user(user)
                    .dob(parseLocalDate(req.getParameter("dob")))
                    .pob(trim(req.getParameter("pob")))
                    .gender(trim(req.getParameter("gender")))
                    .phoneNumber(trim(req.getParameter("phoneNumber")))
                    .citizenId(trim(req.getParameter("citizenId")))
                    .nation(trim(req.getParameter("nation")))
                    .religion(trim(req.getParameter("religion")))
                    .nationality(trim(req.getParameter("nationality")))
                    .address(trim(req.getParameter("address")))
                    .classId(trim(req.getParameter("classId")))
                    .build();

            studentService.create(student);
            resp.sendRedirect(req.getContextPath() + "/admin/students");

        } catch (ServiceException e) {
            setErrorAndForward(req, resp, e.getMessage(), VIEW_ADD);
        }
    }

    private void handleEditStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        if (userId == null || userId.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/admin/students");
            return;
        }

        try {
            Student student = studentService.getById(userId);
            User user = student.getUser();

            String newEmail = trim(req.getParameter("email"));

            if (!user.getEmail().equals(newEmail) && userService.existsByEmail(newEmail)) {
                req.setAttribute("student", student);
                keepFormValues(req);
                setErrorAndForward(req, resp, "admin.student.add.error.emailTaken", VIEW_EDIT);
                return;
            }

            user.setEmail(newEmail);
            user.setFirstName(trim(req.getParameter("firstName")));
            user.setLastName(trim(req.getParameter("lastName")));

            student.setDob(parseLocalDate(req.getParameter("dob")));
            student.setPob(trim(req.getParameter("pob")));
            student.setGender(trim(req.getParameter("gender")));
            student.setPhoneNumber(trim(req.getParameter("phoneNumber")));
            student.setCitizenId(trim(req.getParameter("citizenId")));
            student.setNation(trim(req.getParameter("nation")));
            student.setReligion(trim(req.getParameter("religion")));
            student.setNationality(trim(req.getParameter("nationality")));
            student.setAddress(trim(req.getParameter("address")));
            student.setClassId(trim(req.getParameter("classId")));

            studentService.update(student);
            resp.sendRedirect(req.getContextPath() + "/admin/students");

        } catch (ServiceException e) {
            try {
                req.setAttribute("student", studentService.getById(userId));
            } catch (Exception ex) {}

            setErrorAndForward(req, resp, e.getMessage(), VIEW_EDIT);
        }
    }

    private void handleDeleteStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String userId = req.getParameter("userId");

            if (userId == null || userId.isEmpty()) {
                resp.sendRedirect(req.getContextPath() + "/admin/students");
                return;
            }

            studentService.delete(userId);
            resp.sendRedirect(req.getContextPath() + "/admin/students");

        } catch (ServiceException e) {
            req.setAttribute("error", "Delete failed: " + e.getMessage());
            list(req, resp);
        }
    }

    private void setErrorAndForward(HttpServletRequest req, HttpServletResponse resp, String error, String jspPage) throws ServletException, IOException {
        req.setAttribute("error", error);
        req.setAttribute("content", jspPage);
        String title = jspPage.equals(VIEW_ADD) ? "admin.title.student.add" :
                jspPage.equals(VIEW_EDIT) ? "admin.title.student.edit" : "admin.title.student";
        req.setAttribute("title", title);

        req.getRequestDispatcher(VIEW_LAYOUT).forward(req, resp);
    }

    private void keepFormValues(HttpServletRequest req) {
        String[] fields = {
                "username", "email", "firstName", "lastName", "pob",
                "gender", "phoneNumber", "citizenId", "nation",
                "religion", "nationality", "address", "classId", "dob"
        };
        for (String field : fields) {
            req.setAttribute(field, req.getParameter(field));
        }
    }

    private String trim(String value) {
        return value != null ? value.trim() : "";
    }

    private LocalDate parseLocalDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }
}