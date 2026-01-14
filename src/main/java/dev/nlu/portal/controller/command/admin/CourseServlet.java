package dev.nlu.portal.controller.command.admin;

import dev.nlu.portal.exception.ServiceException;
import dev.nlu.portal.model.Course;
import dev.nlu.portal.service.CourseService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/courses/*"})
public class CourseServlet extends HttpServlet {
    private static final String VIEW_LAYOUT = "/WEB-INF/views/layout/admin/main.jsp";
    private static final String VIEW_LIST = "/WEB-INF/views/pages/admin/course/list.jsp";
    private static final String VIEW_ADD = "/WEB-INF/views/pages/admin/course/add.jsp";
    private static final String VIEW_EDIT = "/WEB-INF/views/pages/admin/course/edit.jsp";

    private final CourseService courseService = new CourseService();

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
                req.setAttribute("title", "admin.title.course.add");
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
                case "/add" -> handleAddCourse(req, resp);
                case "/edit" -> handleEditCourse(req, resp);
                case "/delete" -> handleDeleteCourse(req, resp);
                default -> resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (ServiceException e) {
            req.setAttribute("error", e.getMessage());
            req.setAttribute("content", VIEW_LIST);
            req.getRequestDispatcher(VIEW_LAYOUT).forward(req, resp);
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Course> courses = courseService.getAll();

        req.setAttribute("courses", courses);
        req.setAttribute("content", VIEW_LIST);
        req.setAttribute("title", "admin.title.course.list");
        req.getRequestDispatcher(VIEW_LAYOUT).forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseId = req.getParameter("courseId");

        if (courseId == null || courseId.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/admin/courses");
            return;
        }

        try {
            Course course = courseService.getById(courseId);
            req.setAttribute("course", course);

            req.setAttribute("content", VIEW_EDIT);
            req.setAttribute("title", "admin.title.course.edit");
            req.getRequestDispatcher(VIEW_LAYOUT).forward(req, resp);
        } catch (ServiceException e) {
            resp.sendRedirect(req.getContextPath() + "/admin/courses");
        }
    }

    private void handleAddCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = trim(req.getParameter("code"));
        String name = trim(req.getParameter("name"));
        String soTinChiStr = trim(req.getParameter("soTinChi"));
        String lyThuyetStr = trim(req.getParameter("lyThuyet"));
        String thucHanhStr = trim(req.getParameter("thucHanh"));
        String url = trim(req.getParameter("url"));

        keepFormValues(req);

        // Validation
        if (code.isEmpty()) {
            setErrorAndForward(req, resp, "admin.course.add.error.codeRequired", VIEW_ADD);
            return;
        }

        if (name.isEmpty()) {
            setErrorAndForward(req, resp, "admin.course.add.error.nameRequired", VIEW_ADD);
            return;
        }

        if (courseService.existsByCode(code)) {
            setErrorAndForward(req, resp, "admin.course.add.error.codeTaken", VIEW_ADD);
            return;
        }

        try {
            int soTinChi = parseInt(soTinChiStr);
            int lyThuyet = parseInt(lyThuyetStr);
            int thucHanh = parseInt(thucHanhStr);

            Course course = Course.builder()
                    .code(code)
                    .name(name)
                    .soTinChi(soTinChi)
                    .lyThuyet(lyThuyet)
                    .thucHanh(thucHanh)
                    .url(url)
                    .build();

            courseService.create(course);
            resp.sendRedirect(req.getContextPath() + "/admin/courses");

        } catch (ServiceException e) {
            setErrorAndForward(req, resp, e.getMessage(), VIEW_ADD);
        }
    }

    private void handleEditCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseId = req.getParameter("courseId");
        if (courseId == null || courseId.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/admin/courses");
            return;
        }

        try {
            Course course = courseService.getById(courseId);

            String code = trim(req.getParameter("code"));
            String name = trim(req.getParameter("name"));
            String soTinChiStr = trim(req.getParameter("soTinChi"));
            String lyThuyetStr = trim(req.getParameter("lyThuyet"));
            String thucHanhStr = trim(req.getParameter("thucHanh"));
            String url = trim(req.getParameter("url"));

            // Check if code is changed and if it's taken
            if (!course.getCode().equals(code) && courseService.existsByCode(code)) {
                req.setAttribute("course", course);
                keepFormValues(req);
                setErrorAndForward(req, resp, "admin.course.add.error.codeTaken", VIEW_EDIT);
                return;
            }

            course.setCode(code);
            course.setName(name);
            course.setSoTinChi(parseInt(soTinChiStr));
            course.setLyThuyet(parseInt(lyThuyetStr));
            course.setThucHanh(parseInt(thucHanhStr));
            course.setUrl(url);

            courseService.update(course);
            resp.sendRedirect(req.getContextPath() + "/admin/courses");

        } catch (ServiceException e) {
            try {
                req.setAttribute("course", courseService.getById(courseId));
            } catch (Exception ex) {
                // Ignore
            }
            setErrorAndForward(req, resp, e.getMessage(), VIEW_EDIT);
        }
    }

    private void handleDeleteCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String courseId = req.getParameter("courseId");

            if (courseId == null || courseId.isEmpty()) {
                resp.sendRedirect(req.getContextPath() + "/admin/courses");
                return;
            }

            courseService.delete(courseId);
            resp.sendRedirect(req.getContextPath() + "/admin/courses");

        } catch (ServiceException e) {
            req.setAttribute("error", "Delete failed: " + e.getMessage());
            list(req, resp);
        }
    }

    private void setErrorAndForward(HttpServletRequest req, HttpServletResponse resp, String error, String jspPage) throws ServletException, IOException {
        req.setAttribute("error", error);
        req.setAttribute("content", jspPage);
        String title = jspPage.equals(VIEW_ADD) ? "admin.title.course.add" :
                jspPage.equals(VIEW_EDIT) ? "admin.title.course.edit" : "admin.title.course.list";
        req.setAttribute("title", title);

        req.getRequestDispatcher(VIEW_LAYOUT).forward(req, resp);
    }

    private void keepFormValues(HttpServletRequest req) {
        String[] fields = {
                "code", "name", "soTinChi", "lyThuyet", "thucHanh", "url"
        };
        for (String field : fields) {
            req.setAttribute(field, req.getParameter(field));
        }
    }

    private String trim(String value) {
        return value != null ? value.trim() : "";
    }

    private int parseInt(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}