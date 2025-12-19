package dev.nlu.portal.controller;

import dev.nlu.portal.dao.StudentDAO;
import dev.nlu.portal.model.Student;
import dev.nlu.portal.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    StudentService ser = new StudentService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Student freshStudent = ser.find((String) req.getSession().getAttribute("username"));
        req.setAttribute("student", freshStudent);
        req.getRequestDispatcher("profile.jsp").forward(req, resp);

    }
}
