package dev.nlu.portal.controller.api;

import dev.nlu.portal.model.Grade;
import dev.nlu.portal.service.GradeServiceImpl;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GradeApiServlet extends HttpServlet {
    private final GradeServiceImpl service = new GradeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String idParam = req.getParameter("id");
        if (idParam != null) {
            Long id = Long.valueOf(idParam);
            Grade g = service.findById(id);
            if (g == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.write("{\"error\":\"not_found\"}");
            } else {
                out.write(toJson(g));
            }
            return;
        }
        List<Grade> list = service.findAll();
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
                    Grade g = parseGrade(req);
                    service.save(g);
                    out.write(toJson(g));
                    break;
                }
                case "update": {
                    Long id = Long.valueOf(req.getParameter("id"));
                    Grade g = parseGrade(req);
                    g.setId(id);
                    service.update(g);
                    out.write(toJson(g));
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

    private Grade parseGrade(HttpServletRequest req) {
        Grade g = new Grade();
        String rid = req.getParameter("registrationId");
        g.setRegistrationId(rid == null || rid.isBlank() ? null : Long.valueOf(rid));
        g.setMidtermScore(parseDouble(req.getParameter("midtermScore")));
        g.setFinalScore(parseDouble(req.getParameter("finalScore")));
        g.setOverallScore(parseDouble(req.getParameter("overallScore")));
        g.setLetterGrade(req.getParameter("letterGrade"));
        return g;
    }

    private Double parseDouble(String s) { return s == null || s.isBlank() ? null : Double.valueOf(s); }

    private String toJson(Grade g) {
        StringBuilder b = new StringBuilder();
        b.append("{")
                .append("\"id\":").append(g.getId() == null ? "null" : g.getId()).append(',')
                .append("\"registrationId\":").append(g.getRegistrationId() == null ? "null" : g.getRegistrationId()).append(',')
                .append("\"midtermScore\":").append(g.getMidtermScore() == null ? "null" : g.getMidtermScore()).append(',')
                .append("\"finalScore\":").append(g.getFinalScore() == null ? "null" : g.getFinalScore()).append(',')
                .append("\"overallScore\":").append(g.getOverallScore() == null ? "null" : g.getOverallScore()).append(',')
                .append("\"letterGrade\":\"").append(ns(g.getLetterGrade())).append("\"")
                .append("}");
        return b.toString();
    }

    private String ns(String s) { return s == null ? "" : s.replace("\"", "\\\""); }
}