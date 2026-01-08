package dev.nlu.portal.controller.command.admin;

import dev.nlu.portal.exception.ServiceException;
import dev.nlu.portal.model.Lecturer;
import dev.nlu.portal.model.User;
import dev.nlu.portal.service.LecturerService;
import dev.nlu.portal.service.UserService;
import dev.nlu.portal.utils.PasswordUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/lecturers/*"})
public class LecturerServlet extends HttpServlet {
    private static final String VIEW_LAYOUT = "/WEB-INF/views/layout/admin/main.jsp";
    private static final String VIEW_LIST = "/WEB-INF/views/pages/admin/lecturer/list.jsp";
    private static final String VIEW_ADD = "/WEB-INF/views/pages/admin/lecturer/add.jsp";
    private static final String VIEW_EDIT = "/WEB-INF/views/pages/admin/lecturer/edit.jsp";

    private final LecturerService lecturerService = new LecturerService();
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
                req.setAttribute("title", "admin.title.lecturer.add");
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
                case "/add" -> handleAddLecturer(req, resp);
                case "/edit" -> handleEditLecturer(req, resp);
                case "/delete" -> handleDeleteLecturer(req, resp);
                default -> resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (ServiceException e) {
            req.setAttribute("error", e.getMessage());
            req.setAttribute("content", VIEW_LIST);
            req.getRequestDispatcher(VIEW_LAYOUT).forward(req, resp);
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lecturer> lecturers = lecturerService.getAll();

        req.setAttribute("lecturers", lecturers);
        req.setAttribute("content", VIEW_LIST);
        req.setAttribute("title", "admin.title.lecturer.list");
        req.getRequestDispatcher(VIEW_LAYOUT).forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");

        if (userId == null || userId.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/admin/lecturers");
            return;
        }

        try {
            Lecturer lecturer = lecturerService.getById(userId);
            req.setAttribute("lecturer", lecturer);

            req.setAttribute("content", VIEW_EDIT);
            req.setAttribute("title", "admin.title.lecturer.edit");
            req.getRequestDispatcher(VIEW_LAYOUT).forward(req, resp);
        } catch (ServiceException e) {
            resp.sendRedirect(req.getContextPath() + "/admin/lecturers");
        }
    }

    private void handleAddLecturer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = trim(req.getParameter("username"));
        String emailWork = trim(req.getParameter("emailWork"));
        String emailPersonal = trim(req.getParameter("emailPersonal"));
        String fullName = trim(req.getParameter("fullName"));

        keepFormValues(req);

        // Validation
        if (username.isEmpty()) {
            setErrorAndForward(req, resp, "admin.lecturer.add.error.usernameRequired", VIEW_ADD);
            return;
        }

        if (emailWork.isEmpty()) {
            setErrorAndForward(req, resp, "admin.lecturer.add.error.emailRequired", VIEW_ADD);
            return;
        }

        if (userService.existsByUsername(username)) {
            setErrorAndForward(req, resp, "admin.lecturer.add.error.usernameTaken", VIEW_ADD);
            return;
        }

        if (userService.existsByEmail(emailWork)) {
            setErrorAndForward(req, resp, "admin.lecturer.add.error.emailTaken", VIEW_ADD);
            return;
        }

        try {
            String firstName = fullName;
            String lastName = "";

            // Parse full name into first and last name
            if (fullName.contains(" ")) {
                int lastSpaceIndex = fullName.lastIndexOf(" ");
                firstName = fullName.substring(lastSpaceIndex + 1);
                lastName = fullName.substring(0, lastSpaceIndex);
            }

            User user = User.builder()
                    .username(username)
                    .hashedPassword(PasswordUtils.hashpw(username))
                    .email(emailWork)
                    .firstName(firstName)
                    .lastName(lastName)
                    .enabled(true)
                    .build();

            Lecturer lecturer = Lecturer.builder()
                    .user(user)
                    .birthYear(parseInteger(req.getParameter("birthYear")))
                    .gender(trim(req.getParameter("gender")))
                    .identityCard(trim(req.getParameter("identityCard")))
                    .academicRank(trim(req.getParameter("academicRank")))
                    .degree(trim(req.getParameter("degree")))
                    .specialization(trim(req.getParameter("specialization")))
                    .position(trim(req.getParameter("position")))
                    .department(trim(req.getParameter("department")))
                    .workAgency(trim(req.getParameter("workAgency")))
                    .agencyAddress(trim(req.getParameter("agencyAddress")))
                    .phoneFixed(trim(req.getParameter("phoneFixed")))
                    .phoneMobile(trim(req.getParameter("phoneMobile")))
                    .fax(trim(req.getParameter("fax")))
                    .bankAccountNumber(trim(req.getParameter("bankAccountNumber")))
                    .bankName(trim(req.getParameter("bankName")))
                    .bankBranch(trim(req.getParameter("bankBranch")))
                    .build();

            lecturerService.create(lecturer);
            resp.sendRedirect(req.getContextPath() + "/admin/lecturers");

        } catch (ServiceException e) {
            setErrorAndForward(req, resp, e.getMessage(), VIEW_ADD);
        }
    }

    private void handleEditLecturer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        if (userId == null || userId.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/admin/lecturers");
            return;
        }

        try {
            Lecturer lecturer = lecturerService.getById(userId);
            User user = lecturer.getUser();

            String newEmailWork = trim(req.getParameter("emailWork"));

            if (!user.getEmail().equals(newEmailWork) && userService.existsByEmail(newEmailWork)) {
                req.setAttribute("lecturer", lecturer);
                keepFormValues(req);
                setErrorAndForward(req, resp, "admin.lecturer.add.error.emailTaken", VIEW_EDIT);
                return;
            }

            String fullName = trim(req.getParameter("fullName"));
            String firstName = fullName;
            String lastName = "";

            if (fullName.contains(" ")) {
                int lastSpaceIndex = fullName.lastIndexOf(" ");
                firstName = fullName.substring(lastSpaceIndex + 1);
                lastName = fullName.substring(0, lastSpaceIndex);
            }

            user.setEmail(newEmailWork);
            user.setFirstName(firstName);
            user.setLastName(lastName);

            lecturer.setBirthYear(parseInteger(req.getParameter("birthYear")));
            lecturer.setGender(trim(req.getParameter("gender")));
            lecturer.setIdentityCard(trim(req.getParameter("identityCard")));
            lecturer.setAcademicRank(trim(req.getParameter("academicRank")));
            lecturer.setDegree(trim(req.getParameter("degree")));
            lecturer.setSpecialization(trim(req.getParameter("specialization")));
            lecturer.setPosition(trim(req.getParameter("position")));
            lecturer.setDepartment(trim(req.getParameter("department")));
            lecturer.setWorkAgency(trim(req.getParameter("workAgency")));
            lecturer.setAgencyAddress(trim(req.getParameter("agencyAddress")));
            lecturer.setPhoneFixed(trim(req.getParameter("phoneFixed")));
            lecturer.setPhoneMobile(trim(req.getParameter("phoneMobile")));
            lecturer.setFax(trim(req.getParameter("fax")));
            lecturer.setBankAccountNumber(trim(req.getParameter("bankAccountNumber")));
            lecturer.setBankName(trim(req.getParameter("bankName")));
            lecturer.setBankBranch(trim(req.getParameter("bankBranch")));

            lecturerService.update(lecturer);
            resp.sendRedirect(req.getContextPath() + "/admin/lecturers");

        } catch (ServiceException e) {
            try {
                req.setAttribute("lecturer", lecturerService.getById(userId));
            } catch (Exception ex) {
                // Ignore
            }
            setErrorAndForward(req, resp, e.getMessage(), VIEW_EDIT);
        }
    }

    private void handleDeleteLecturer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String userId = req.getParameter("userId");

            if (userId == null || userId.isEmpty()) {
                resp.sendRedirect(req.getContextPath() + "/admin/lecturers");
                return;
            }

            lecturerService.delete(userId);
            resp.sendRedirect(req.getContextPath() + "/admin/lecturers");

        } catch (ServiceException e) {
            req.setAttribute("error", "Delete failed: " + e.getMessage());
            list(req, resp);
        }
    }

    private void setErrorAndForward(HttpServletRequest req, HttpServletResponse resp, String error, String jspPage) throws ServletException, IOException {
        req.setAttribute("error", error);
        req.setAttribute("content", jspPage);
        String title = jspPage.equals(VIEW_ADD) ? "admin.title.lecturer.add" :
                jspPage.equals(VIEW_EDIT) ? "admin.title.lecturer.edit" : "admin.title.lecturer.list";
        req.setAttribute("title", title);

        req.getRequestDispatcher(VIEW_LAYOUT).forward(req, resp);
    }

    private void keepFormValues(HttpServletRequest req) {
        String[] fields = {
                "username", "emailWork", "emailPersonal", "fullName",
                "birthYear", "gender", "identityCard",
                "academicRank", "degree", "specialization", "position",
                "department", "workAgency", "agencyAddress",
                "phoneFixed", "phoneMobile", "fax",
                "bankAccountNumber", "bankName", "bankBranch"
        };
        for (String field : fields) {
            req.setAttribute(field, req.getParameter(field));
        }
    }

    private String trim(String value) {
        return value != null ? value.trim() : "";
    }

    private Integer parseInteger(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
