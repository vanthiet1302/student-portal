package dev.nlu.portal.controller.api;

import dev.nlu.portal.model.Announcement;
import dev.nlu.portal.service.AnnouncementServiceImpl;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AnnouncementApiServlet extends HttpServlet {
    private final AnnouncementServiceImpl service = new AnnouncementServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String idParam = req.getParameter("id");
        if (idParam != null) {
            Long id = Long.valueOf(idParam);
            Announcement a = service.findById(id);
            if (a == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.write("{\"error\":\"not_found\"}");
            } else {
                out.write(toJson(a));
            }
            return;
        }
        List<Announcement> list = service.findAll();
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
                case "delete": {
                    Long id = Long.valueOf(req.getParameter("id"));
                    service.delete(id);
                    out.write("{\"status\":\"deleted\"}");
                    break;
                }
                default:
                    resp.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
                    out.write("{\"error\":\"not_implemented\"}");
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"" + e.getMessage().replace("\"", "'") + "\"}");
        }
    }

    private String toJson(Announcement a) {
        StringBuilder b = new StringBuilder();
        b.append("{")
                .append("\"id\":").append(a.getId() == null ? "null" : a.getId()).append(',')
                .append("\"title\":\"").append(ns(a.getTitle())).append("\",")
                .append("\"content\":\"").append(ns(a.getContent())).append("\",")
                .append("\"postedBy\":").append(a.getPostedBy() == null ? "null" : a.getPostedBy()).append(',')
                .append("\"postedAt\":\"").append(a.getPostedAt() == null ? "" : a.getPostedAt()).append("\",")
                .append("\"pinned\":").append(a.isPinned())
                .append("}");
        return b.toString();
    }

    private String ns(String s) { return s == null ? "" : s.replace("\"", "\\\""); }
}