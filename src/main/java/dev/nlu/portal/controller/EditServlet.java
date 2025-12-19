package dev.nlu.portal.controller;

import dev.nlu.portal.model.Student;
import dev.nlu.portal.service.StudentService;
import dev.nlu.portal.utils.PasswordUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    StudentService ser = new StudentService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        Student s = new Student();
        Student old= ser.find(username);
        s.setUsername(username);
        s.setFirstname(req.getParameter("firstname"));
        s.setLastname(req.getParameter("lastname"));
        s.setHashPassword(old.getHashPassword());
        s.setPrimaryEmail(req.getParameter("primary_email"));
        s.setClassId(req.getParameter("classid"));
        s.setDob(Date.valueOf(req.getParameter("dob")));
        String genderParam = req.getParameter("gender");
        if (genderParam != null) {
            s.setMale(Boolean.parseBoolean(genderParam)); // true=Nam, false=Nữ
        }
        s.setStatus(req.getParameter("status"));
        s.setPhone(req.getParameter("phone"));
        s.setCitizenId(req.getParameter("citizen_id"));
        s.setNation(req.getParameter("nation"));
        s.setReligion(req.getParameter("religion"));
        s.setPob(req.getParameter("pob"));
        s.setNationality(req.getParameter("nationality"));
        s.setAddress(req.getParameter("address"));
        s.setAvatarUrl(req.getParameter("avatar_url"));

        int rows = ser.update(s);
        System.out.println("UPDATE RESULT = " + rows);
        System.out.println("Student male = " + s.isMale());
        Student updated = ser.find(s.getUsername());
        req.setAttribute("student", updated);

        req.getRequestDispatcher("profile.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        Student s = ser.find(username);
        req.setAttribute("student", s);

        req.getRequestDispatcher("edit.jsp").forward(req, resp);
    }
}

