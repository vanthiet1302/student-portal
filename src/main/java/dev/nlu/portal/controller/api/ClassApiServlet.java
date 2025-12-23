package dev.nlu.portal.controller.api;

import dev.nlu.portal.model.Class;
import dev.nlu.portal.service.ClassServiceImpl;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ClassApiServlet extends HttpServlet {
    private final ClassServiceImpl service = new ClassServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String idParam = req.getParameter("id");
        if (idParam != null) {
            Long id = Long.valueOf(idParam);
            Class c = service.findById(id);
            if (c == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.write("{\"error\":\"not_found\"}");
            } else {
                out.write(toJson(c));
            }
            return;
        }
        List<Class> list = service.findAll();
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
                    Class c = parseClass(req);
                    service.save(c);
                    out.write(toJson(c));
                    break;
                }
                case "update": {
                    Long id = Long.valueOf(req.getParameter("id"));
                    Class c = parseClass(req);
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

    private Class parseClass(HttpServletRequest req) {
        Class c = new Class();
        c.setClassCode(req.getParameter("classCode"));
        c.setCourseId(parseLong(req.getParameter("courseId")));
        c.setTeacherId(parseLong(req.getParameter("teacherId")));
        c.setSemesterId(parseLong(req.getParameter("semesterId")));
        String max = req.getParameter("maxStudents");
        String cur = req.getParameter("currentStudents");
        c.setMaxStudents(max == null || max.isBlank() ? 0 : Integer.parseInt(max));
        c.setCurrentStudents(cur == null || cur.isBlank() ? 0 : Integer.parseInt(cur));
        c.setSchedule(req.getParameter("schedule"));
        c.setWeekRange(req.getParameter("weekRange"));
        return c;
    }

    private Long parseLong(String s) { return s == null || s.isBlank() ? null : Long.valueOf(s); }

    private String toJson(Class c) {
        StringBuilder b = new StringBuilder();
        b.append("{")
                .append("\"id\":").append(c.getId() == null ? "null" : c.getId()).append(',')
                .append("\"classCode\":\"").append(ns(c.getClassCode())).append("\",")
                .append("\"courseId\":").append(c.getCourseId() == null ? "null" : c.getCourseId()).append(',')
                .append("\"teacherId\":").append(c.getTeacherId() == null ? "null" : c.getTeacherId()).append(',')
                .append("\"semesterId\":").append(c.getSemesterId() == null ? "null" : c.getSemesterId()).append(',')
                .append("\"maxStudents\":").append(c.getMaxStudents()).append(',')
                .append("\"currentStudents\":").append(c.getCurrentStudents()).append(',')
                .append("\"schedule\":\"").append(ns(c.getSchedule())).append("\",")
                .append("\"weekRange\":\"").append(ns(c.getWeekRange())).append("\"")
                .append("}");
        return b.toString();
    }

    private String ns(String s) { return s == null ? "" : s.replace("\"", "\\\""); }
}