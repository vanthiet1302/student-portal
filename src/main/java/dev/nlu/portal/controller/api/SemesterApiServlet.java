package dev.nlu.portal.controller.api;

import dev.nlu.portal.model.Semester;
import dev.nlu.portal.service.SemesterServiceImpl;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

public class SemesterApiServlet extends HttpServlet {
    private final SemesterServiceImpl service = new SemesterServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String idParam = req.getParameter("id");
        if (idParam != null) {
            Long id = Long.valueOf(idParam);
            Semester s = service.findById(id);
            if (s == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.write("{\"error\":\"not_found\"}");
            } else {
                out.write(toJson(s));
            }
            return;
        }
        List<Semester> list = service.findAll();
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
                    Semester s = parseSemester(req);
                    service.save(s);
                    out.write(toJson(s));
                    break;
                }
                case "update": {
                    Long id = Long.valueOf(req.getParameter("id"));
                    Semester s = parseSemester(req);
                    s.setId(id);
                    service.update(s);
                    out.write(toJson(s));
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

    private Semester parseSemester(HttpServletRequest req) {
        Semester s = new Semester();
        s.setSemesterCode(req.getParameter("semesterCode"));
        s.setName(req.getParameter("name"));
        String sd = req.getParameter("startDate");
        String ed = req.getParameter("endDate");
        if (sd != null && !sd.isBlank()) s.setStartDate(LocalDate.parse(sd));
        if (ed != null && !ed.isBlank()) s.setEndDate(LocalDate.parse(ed));
        String current = req.getParameter("current");
        s.setCurrent(current != null && (current.equalsIgnoreCase("true") || current.equals("1")));
        return s;
    }

    private String toJson(Semester s) {
        StringBuilder b = new StringBuilder();
        b.append("{")
                .append("\"id\":").append(s.getId() == null ? "null" : s.getId()).append(',')
                .append("\"semesterCode\":\"").append(ns(s.getSemesterCode())).append("\",")
                .append("\"name\":\"").append(ns(s.getName())).append("\",")
                .append("\"startDate\":\"").append(s.getStartDate() == null ? "" : s.getStartDate()).append("\",")
                .append("\"endDate\":\"").append(s.getEndDate() == null ? "" : s.getEndDate()).append("\",")
                .append("\"current\":").append(s.isCurrent())
                .append("}");
        return b.toString();
    }

    private String ns(String s) { return s == null ? "" : s.replace("\"", "\\\""); }
}