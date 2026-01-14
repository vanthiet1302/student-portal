package dev.nlu.portal.controller.command.admin;

import dev.nlu.portal.exception.ServiceException;
import dev.nlu.portal.model.Faculty;
import dev.nlu.portal.service.FacultyService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/faculties/*"})
public class FacultyServlet extends HttpServlet {
    private static final String VIEW_LAYOUT = "/WEB-INF/views/layout/admin/main.jsp";
    private static final String VIEW_LIST = "/WEB-INF/views/pages/admin/faculty/list.jsp";
    private static final String VIEW_ADD = "/WEB-INF/views/pages/admin/faculty/add.jsp";
    private static final String VIEW_EDIT = "/WEB-INF/views/pages/admin/faculty/edit.jsp";

    private final FacultyService facultyService = new FacultyService();

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
                req.setAttribute("title", "admin.title.faculty.add");
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
                case "/add" -> handleAddFaculty(req, resp);
                case "/edit" -> handleEditFaculty(req, resp);
                case "/delete" -> handleDeleteFaculty(req, resp);
                default -> resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (ServiceException e) {
            req.setAttribute("error", e.getMessage());
            req.setAttribute("content", VIEW_LIST);
            req.getRequestDispatcher(VIEW_LAYOUT).forward(req, resp);
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Faculty> faculties = facultyService.getAll();

        req.setAttribute("faculties", faculties);
        req.setAttribute("content", VIEW_LIST);
        req.setAttribute("title", "admin.title.faculty.list");
        req.getRequestDispatcher(VIEW_LAYOUT).forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String facultyId = req.getParameter("facultyId");

        if (facultyId == null || facultyId.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/admin/faculties");
            return;
        }

        try {
            Faculty faculty = facultyService.getById(facultyId);
            req.setAttribute("faculty", faculty);

            req.setAttribute("content", VIEW_EDIT);
            req.setAttribute("title", "admin.title.faculty.edit");
            req.getRequestDispatcher(VIEW_LAYOUT).forward(req, resp);
        } catch (ServiceException e) {
            resp.sendRedirect(req.getContextPath() + "/admin/faculties");
        }
    }

    private void handleAddFaculty(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = trim(req.getParameter("code"));
        String name = trim(req.getParameter("name"));
        String establishedDateStr = trim(req.getParameter("establishedDate"));

        keepFormValues(req);

        // Validation
        if (code.isEmpty()) {
            setErrorAndForward(req, resp, "admin.faculty.add.error.codeRequired", VIEW_ADD);
            return;
        }

        if (name.isEmpty()) {
            setErrorAndForward(req, resp, "admin.faculty.add.error.nameRequired", VIEW_ADD);
            return;
        }

        if (facultyService.existsByCode(code)) {
            setErrorAndForward(req, resp, "admin.faculty.add.error.codeTaken", VIEW_ADD);
            return;
        }

        try {
            Faculty faculty = Faculty.builder()
                    .code(code)
                    .name(name)
                    .establishedDate(parseLocalDate(establishedDateStr))
                    .build();

            facultyService.create(faculty);
            resp.sendRedirect(req.getContextPath() + "/admin/faculties");

        } catch (ServiceException e) {
            setErrorAndForward(req, resp, e.getMessage(), VIEW_ADD);
        }
    }

    private void handleEditFaculty(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String facultyId = req.getParameter("facultyId");
        if (facultyId == null || facultyId.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/admin/faculties");
            return;
        }

        try {
            Faculty faculty = facultyService.getById(facultyId);

            String code = trim(req.getParameter("code"));
            String name = trim(req.getParameter("name"));
            String establishedDateStr = trim(req.getParameter("establishedDate"));

            // Check if code is changed and if it's taken
            if (!faculty.getCode().equals(code) && facultyService.existsByCode(code)) {
                req.setAttribute("faculty", faculty);
                keepFormValues(req);
                setErrorAndForward(req, resp, "admin.faculty.add.error.codeTaken", VIEW_EDIT);
                return;
            }

            faculty.setCode(code);
            faculty.setName(name);
            faculty.setEstablishedDate(parseLocalDate(establishedDateStr));

            facultyService.update(faculty);
            resp.sendRedirect(req.getContextPath() + "/admin/faculties");

        } catch (ServiceException e) {
            try {
                req.setAttribute("faculty", facultyService.getById(facultyId));
            } catch (Exception ex) {
                // Ignore
            }
            setErrorAndForward(req, resp, e.getMessage(), VIEW_EDIT);
        }
    }

    private void handleDeleteFaculty(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String facultyId = req.getParameter("facultyId");

            if (facultyId == null || facultyId.isEmpty()) {
                resp.sendRedirect(req.getContextPath() + "/admin/faculties");
                return;
            }

            facultyService.delete(facultyId);
            resp.sendRedirect(req.getContextPath() + "/admin/faculties");

        } catch (ServiceException e) {
            req.setAttribute("error", "Delete failed: " + e.getMessage());
            list(req, resp);
        }
    }

    private void setErrorAndForward(HttpServletRequest req, HttpServletResponse resp, String error, String jspPage) throws ServletException, IOException {
        req.setAttribute("error", error);
        req.setAttribute("content", jspPage);
        String title = jspPage.equals(VIEW_ADD) ? "admin.title.faculty.add" :
                jspPage.equals(VIEW_EDIT) ? "admin.title.faculty.edit" : "admin.title.faculty.list";
        req.setAttribute("title", title);

        req.getRequestDispatcher(VIEW_LAYOUT).forward(req, resp);
    }

    private void keepFormValues(HttpServletRequest req) {
        String[] fields = {
                "code", "name", "establishedDate"
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