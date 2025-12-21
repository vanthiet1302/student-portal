package dev.nlu.portal.controller.api;

import dev.nlu.portal.model.Course;
import dev.nlu.portal.service.CourseServiceImpl;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CourseApiServlet extends HttpServlet {
    private final CourseServiceImpl service = new CourseServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String idParam = req.getParameter("id");
        if (idParam != null) {
            Long id = Long.valueOf(idParam);
            Course c = service.findById(id);
            if (c == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.write("{\"error\":\"not_found\"}");
            } else {
                out.write(toJson(c));
            }
            return;
        }
        List<Course> list = service.findAll();
        out.write("[");
        for (int i = 0; i < list.size(); i++) {
            out.write(toJson(list.get(i)));
            if (i < list.size() - 1) out.write(",");
        }
        out.write("]");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=UTF-8");
        String action = req.getParameter("action");
        if (action == null) action = "create";
        try (PrintWriter out = resp.getWriter()) {
            switch (action) {
                case "create": {
                    Course c = parseCourse(req);
                    service.save(c);
                    out.write(toJson(c));
                    break;
                }
                case "update": {
                    Long id = Long.valueOf(req.getParameter("id"));
                    Course c = parseCourse(req);
                    c.setId(id);
                    service.update(c);
                    out.write(toJson(c));
                    break;
                }
                case "delete": {
                    Long id = Long.valueOf(req.getParameter("id"));
                    service.delete(id);
                    out.write("{\"status\":\"deleted\"}");
                    break;
                }
                default:
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    out.write("{\"error\":\"invalid_action\"}");
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"" + e.getMessage().replace("\"", "'") + "\"}");
        }
    }

    private Course parseCourse(HttpServletRequest req) {
        Course c = new Course();
        c.setCourseCode(req.getParameter("courseCode"));
        c.setName(req.getParameter("name"));
        String credits = req.getParameter("credits");
        c.setCredits(credits == null || credits.isBlank() ? 0 : Integer.parseInt(credits));
        c.setDepartment(req.getParameter("department"));
        return c;
    }

    private String toJson(Course c) {
        StringBuilder b = new StringBuilder();
        b.append("{")
                .append("\"id\":").append(c.getId() == null ? "null" : c.getId()).append(',')
                .append("\"courseCode\":\"").append(ns(c.getCourseCode())).append("\",")
                .append("\"name\":\"").append(ns(c.getName())).append("\",")
                .append("\"credits\":").append(c.getCredits()).append(',')
                .append("\"department\":\"").append(ns(c.getDepartment())).append("\"")
                .append("}");
        return b.toString();
    }

    private String ns(String s) { return s == null ? "" : s.replace("\"", "\\\""); }
}