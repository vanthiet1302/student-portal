package dev.nlu.portal.controller.command.admin;

import dev.nlu.portal.exception.ServiceException;
import dev.nlu.portal.model.AcademicClass;
import dev.nlu.portal.model.Faculty;
import dev.nlu.portal.model.Lecturer;
import dev.nlu.portal.service.AcademicClassService;
import dev.nlu.portal.service.FacultyService;
import dev.nlu.portal.service.LecturerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/academic-classes/*"})
public class AcademicClassServlet extends HttpServlet {
    private static final String VIEW_LAYOUT = "/WEB-INF/views/layout/admin/main.jsp";
    private static final String VIEW_LIST = "/WEB-INF/views/pages/admin/academicClass/list.jsp";
    private static final String VIEW_ADD = "/WEB-INF/views/pages/admin/academicClass/add.jsp";
    private static final String VIEW_EDIT = "/WEB-INF/views/pages/admin/academicClass/edit.jsp";

    private final AcademicClassService classService = new AcademicClassService();
    private final FacultyService facultyService = new FacultyService();
    private final LecturerService lecturerService = new LecturerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo == null || "/".equals(pathInfo)) {
            list(req, resp);
            return;
        }

        switch (pathInfo) {
            case "/add" -> {
                loadFormData(req);
                req.setAttribute("content", VIEW_ADD);
                req.setAttribute("title", "admin.title.academicClass.add");
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
                case "/add" -> handleAddClass(req, resp);
                case "/edit" -> handleEditClass(req, resp);
                case "/delete" -> handleDeleteClass(req, resp);
                default -> resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (ServiceException e) {
            req.setAttribute("error", e.getMessage());
            req.setAttribute("content", VIEW_LIST);
            req.getRequestDispatcher(VIEW_LAYOUT).forward(req, resp);
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AcademicClass> classes = classService.getAll();
        req.setAttribute("classes", classes);
        req.setAttribute("content", VIEW_LIST);
        req.setAttribute("title", "admin.title.academicClass.list");
        req.getRequestDispatcher(VIEW_LAYOUT).forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String classId = req.getParameter("classId");

        if (classId == null || classId.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/admin/academic-classes");
            return;
        }

        try {
            AcademicClass clazz = classService.getById(classId);
            req.setAttribute("academicClass", clazz);
            loadFormData(req);

            req.setAttribute("content", VIEW_EDIT);
            req.setAttribute("title", "admin.title.academicClass.edit");
            req.getRequestDispatcher(VIEW_LAYOUT).forward(req, resp);
        } catch (ServiceException e) {
            resp.sendRedirect(req.getContextPath() + "/admin/academic-classes");
        }
    }

    private void handleAddClass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = trim(req.getParameter("code"));
        String name = trim(req.getParameter("name"));
        String facultyId = trim(req.getParameter("facultyId"));
        String advisorId = trim(req.getParameter("advisorId"));
        String nienKhoa = trim(req.getParameter("nienKhoa"));

        keepFormValues(req);

        // Validation
        if (code.isEmpty()) {
            setErrorAndForward(req, resp, "admin.academicClass.add.error.codeRequired", VIEW_ADD);
            return;
        }

        if (name.isEmpty()) {
            setErrorAndForward(req, resp, "admin.academicClass.add.error.nameRequired", VIEW_ADD);
            return;
        }

        if (facultyId.isEmpty()) {
            setErrorAndForward(req, resp, "admin.academicClass.add.error.facultyIdRequired", VIEW_ADD);
            return;
        }

        if (nienKhoa.isEmpty()) {
            setErrorAndForward(req, resp, "admin.academicClass.add.error.nienKhoaRequired", VIEW_ADD);
            return;
        }

        try {
            AcademicClass clazz = AcademicClass.builder()
                    .code(code)
                    .name(name)
                    .facultyId(facultyId)
                    .advisorId(advisorId.isEmpty() ? null : advisorId)
                    .nienKhoa(nienKhoa)
                    .build();

            classService.create(clazz);
            resp.sendRedirect(req.getContextPath() + "/admin/academic-classes");

        } catch (ServiceException e) {
            setErrorAndForward(req, resp, e.getMessage(), VIEW_ADD);
        }
    }

    private void handleEditClass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String classId = req.getParameter("classId");
        if (classId == null || classId.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/admin/academic-classes");
            return;
        }

        String code = trim(req.getParameter("code"));
        String name = trim(req.getParameter("name"));
        String facultyId = trim(req.getParameter("facultyId"));
        String advisorId = trim(req.getParameter("advisorId"));
        String nienKhoa = trim(req.getParameter("nienKhoa"));

        // Validation
        if (code.isEmpty()) {
            req.setAttribute("academicClass", classService.getById(classId));
            keepFormValues(req);
            setErrorAndForward(req, resp, "admin.academicClass.add.error.codeRequired", VIEW_EDIT);
            return;
        }

        if (name.isEmpty()) {
            req.setAttribute("academicClass", classService.getById(classId));
            keepFormValues(req);
            setErrorAndForward(req, resp, "admin.academicClass.add.error.nameRequired", VIEW_EDIT);
            return;
        }

        try {
            AcademicClass clazz = AcademicClass.builder()
                    .id(classId)
                    .code(code)
                    .name(name)
                    .facultyId(facultyId)
                    .advisorId(advisorId.isEmpty() ? null : advisorId)
                    .nienKhoa(nienKhoa)
                    .build();

            classService.update(clazz);
            resp.sendRedirect(req.getContextPath() + "/admin/academic-classes");

        } catch (ServiceException e) {
            try {
                req.setAttribute("academicClass", classService.getById(classId));
            } catch (Exception ex) {}
            setErrorAndForward(req, resp, e.getMessage(), VIEW_EDIT);
        }
    }

    private void handleDeleteClass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String classId = req.getParameter("classId");

            if (classId == null || classId.isEmpty()) {
                resp.sendRedirect(req.getContextPath() + "/admin/academic-classes");
                return;
            }

            classService.delete(classId);
            resp.sendRedirect(req.getContextPath() + "/admin/academic-classes");

        } catch (ServiceException e) {
            req.setAttribute("error", "Delete failed: " + e.getMessage());
            list(req, resp);
        }
    }

    private void loadFormData(HttpServletRequest req) {
        List<Faculty> faculties = facultyService.getAll();
        List<Lecturer> lecturers = lecturerService.getAll();

        req.setAttribute("faculties", faculties);
        req.setAttribute("lecturers", lecturers);
    }

    private void setErrorAndForward(HttpServletRequest req, HttpServletResponse resp, String error, String jspPage) throws ServletException, IOException {
        req.setAttribute("error", error);
        req.setAttribute("content", jspPage);
        loadFormData(req);
        String title = jspPage.equals(VIEW_ADD) ? "admin.title.academicClass.add" :
                jspPage.equals(VIEW_EDIT) ? "admin.title.academicClass.edit" : "admin.title.academicClass";
        req.setAttribute("title", title);

        req.getRequestDispatcher(VIEW_LAYOUT).forward(req, resp);
    }

    private void keepFormValues(HttpServletRequest req) {
        String[] fields = {
                "code", "name", "facultyId", "advisorId", "nienKhoa"
        };
        for (String field : fields) {
            req.setAttribute(field, req.getParameter(field));
        }
    }

    private String trim(String value) {
        return value != null ? value.trim() : "";
    }
}

