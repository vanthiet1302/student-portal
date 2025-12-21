package dev.nlu.portal.controller.api;

import dev.nlu.portal.model.Degree;
import dev.nlu.portal.model.Teacher;
import dev.nlu.portal.service.TeacherServiceImpl;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TeacherApiServlet extends HttpServlet {
    private final TeacherServiceImpl service = new TeacherServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String idParam = req.getParameter("id");
        if (idParam != null) {
            Long id = Long.valueOf(idParam);
            Teacher t = service.findById(id);
            if (t == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.write("{\"error\":\"not_found\"}");
            } else {
                out.write(toJson(t));
            }
            return;
        }
        List<Teacher> list = service.findAll();
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
                    Teacher t = parseTeacher(req);
                    service.save(t);
                    out.write(toJson(t));
                    break;
                }
                case "update": {
                    Long id = Long.valueOf(req.getParameter("id"));
                    Teacher t = parseTeacher(req);
                    t.setId(id);
                    service.update(t);
                    out.write(toJson(t));
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

    private Teacher parseTeacher(HttpServletRequest req) {
        Teacher t = new Teacher();
        String uid = req.getParameter("userId");
        t.setUserId(uid == null || uid.isBlank() ? null : Long.valueOf(uid));
        t.setTeacherCode(req.getParameter("teacherCode"));
        t.setFullName(req.getParameter("fullName"));
        t.setEmail(req.getParameter("email"));
        t.setPhone(req.getParameter("phone"));
        t.setDepartment(req.getParameter("department"));
        String degree = req.getParameter("degree");
        if (degree != null && !degree.isBlank()) {
            t.setDegree(Degree.valueOf(degree));
        }
        return t;
    }

    private String toJson(Teacher t) {
        StringBuilder b = new StringBuilder();
        b.append("{")
                .append("\"id\":").append(t.getId() == null ? "null" : t.getId()).append(',')
                .append("\"userId\":").append(t.getUserId() == null ? "null" : t.getUserId()).append(',')
                .append("\"teacherCode\":\"").append(ns(t.getTeacherCode())).append("\",")
                .append("\"fullName\":\"").append(ns(t.getFullName())).append("\",")
                .append("\"email\":\"").append(ns(t.getEmail())).append("\",")
                .append("\"phone\":\"").append(ns(t.getPhone())).append("\",")
                .append("\"department\":\"").append(ns(t.getDepartment())).append("\",")
                .append("\"degree\":\"").append(t.getDegree() == null ? "" : t.getDegree().name()).append("\"")
                .append("}");
        return b.toString();
    }

    private String ns(String s) { return s == null ? "" : s.replace("\"", "\\\""); }
}