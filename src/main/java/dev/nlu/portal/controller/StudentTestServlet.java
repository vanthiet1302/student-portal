package dev.nlu.portal.controller;

import dev.nlu.portal.dao.StudentDAO;
import dev.nlu.portal.model.Student;

import dev.nlu.portal.utils.PasswordUtil;
import dev.nlu.portal.utils.UUIDUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/studentTest")
public class StudentTestServlet extends HttpServlet {
    private StudentDAO dao = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");
        Student found = null;

        if (search != null && !search.trim().isEmpty()) {
            found = dao.find(search);
        }
        req.setAttribute("foundStudent", found);
        List<Student> students = dao.findAll();
        req.setAttribute("students", students);

        req.getRequestDispatcher("student-test.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String username = req.getParameter("username");

        if ("add".equals(action)) {
            Student s = new Student();
            s.setId(UUIDUtil.generateUUID());
            s.setUsername(username);
            s.setFirstname(req.getParameter("first_name"));
            s.setLastname(req.getParameter("last_name"));
            s.setPrimaryEmail(req.getParameter("primary_email"));
            s.setHashPassword(PasswordUtil.hashPassword(req.getParameter("hash_password")));
            s.setDob(req.getParameter("dob") != null ? Date.valueOf(req.getParameter("dob")) : null);
            s.setMale("male".equals(req.getParameter("gender")));
            s.setStatus(req.getParameter("status"));
            s.setPhone(req.getParameter("phone"));
            s.setCitizenId(req.getParameter("citizen_id"));
            s.setNation(req.getParameter("nation"));
            s.setReligion(req.getParameter("religion"));
            s.setPob(req.getParameter("pob"));
            s.setNationality(req.getParameter("nationality"));
            s.setAddress(req.getParameter("address"));
            s.setCreateAt(LocalDateTime.now());
            s.setUpdateAt(LocalDateTime.now());
            s.setClassId(req.getParameter("class_id"));
            s.setAvatarUrl(req.getParameter("avatar_url"));
            dao.save(s);

        } else if ("update".equals(action)) {
            Student s = dao.findByUsername(username);
            if (s != null) {
                s.setFirstname(req.getParameter("first_name"));
                s.setLastname(req.getParameter("last_name"));
                s.setPrimaryEmail(req.getParameter("primary_email"));
                s.setUpdateAt(LocalDateTime.now());
                s.setClassId(req.getParameter("class_id"));
                dao.update(s);
            }

        } else if ("delete".equals(action)) {
            Student student = new Student();
            student.setUsername(username);
            dao.delete(student);

        } else if ("find".equals(action)) {
            Student s = dao.findByUsername(username);
            req.setAttribute("foundStudent", s);
        }

        resp.sendRedirect(req.getContextPath() + "/studentTest");
    }
}

