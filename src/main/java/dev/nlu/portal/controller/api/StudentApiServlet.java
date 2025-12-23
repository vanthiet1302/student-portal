package dev.nlu.portal.controller.api;

import dev.nlu.portal.model.Gender;
import dev.nlu.portal.model.Student;
import dev.nlu.portal.service.StudentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

public class StudentApiServlet extends HttpServlet {
    private final StudentServiceImpl service = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String idParam = req.getParameter("id");
        if (idParam != null) {
            Long id = Long.valueOf(idParam);
            Student s = service.findById(id);
            if (s == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.write("{\"error\":\"not_found\"}");
            } else {
                out.write(toJson(s));
            }
            return;
        }
        List<Student> list = service.findAll();
        out.write("[");
        for (int i = 0; i < list.size(); i++) {
            out.write(toJson(list.get(i)));
            if (i < list.size() - 1) out.write(",");
        }
        out.write("]");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=UTF-8");
        String action = req.getParameter("action");
        if (action == null) action = "create";
        try (PrintWriter out = resp.getWriter()) {
            switch (action) {
                case "create": {
                    Student s = parseStudent(req);
                    service.save(s);
                    out.write(toJson(s));
                    break;
                }
                case "update": {
                    Long id = Long.valueOf(req.getParameter("id"));
                    Student existing = service.findById(id);
                    if (existing == null) {
                        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        out.write("{\"error\":\"not_found\"}");
                        break;
                    }
                    Student s = parseStudent(req);
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

    private Student parseStudent(HttpServletRequest req) {
        Student s = new Student();
        s.setUserId(parseLong(req.getParameter("userId")));
        s.setStudentCode(req.getParameter("studentCode"));
        s.setFullName(req.getParameter("fullName"));
        s.setEmail(req.getParameter("email"));
        s.setPhone(req.getParameter("phone"));
        String dob = req.getParameter("dateOfBirth");
        if (dob != null && !dob.isBlank()) {
            s.setDateOfBirth(LocalDate.parse(dob));
        }
        String gender = req.getParameter("gender");
        if (gender != null && !gender.isBlank()) {
            s.setGender(Gender.valueOf(gender));
        }
        s.setAddress(req.getParameter("address"));
        s.setMajor(req.getParameter("major"));
        s.setClassName(req.getParameter("className"));
        return s;
    }

    private Long parseLong(String s) {
        return s == null || s.isBlank() ? null : Long.valueOf(s);
    }

    private String toJson(Student s) {
        StringBuilder b = new StringBuilder();
        b.append("{")
                .append("\"id\":").append(s.getId() == null ? "null" : s.getId()).append(',')
                .append("\"userId\":").append(s.getUserId() == null ? "null" : s.getUserId()).append(',')
                .append("\"studentCode\":\"").append(nullSafe(s.getStudentCode())).append("\",")
                .append("\"fullName\":\"").append(nullSafe(s.getFullName())).append("\",")
                .append("\"email\":\"").append(nullSafe(s.getEmail())).append("\",")
                .append("\"phone\":\"").append(nullSafe(s.getPhone())).append("\",")
                .append("\"dateOfBirth\":\"").append(s.getDateOfBirth() == null ? "" : s.getDateOfBirth().toString()).append("\",")
                .append("\"gender\":\"").append(s.getGender() == null ? "" : s.getGender().name()).append("\",")
                .append("\"address\":\"").append(nullSafe(s.getAddress())).append("\",")
                .append("\"major\":\"").append(nullSafe(s.getMajor())).append("\",")
                .append("\"className\":\"").append(nullSafe(s.getClassName())).append("\"")
                .append("}");
        return b.toString();
    }

    private String nullSafe(String s) {
        return s == null ? "" : s.replace("\"", "\\\"");
    }
}