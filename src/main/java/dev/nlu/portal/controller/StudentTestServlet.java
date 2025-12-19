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


@WebServlet("/studentTest")
public class StudentTestServlet extends HttpServlet {
    StudentService ser= new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        if (username != null && !username.isEmpty()) {
            Student st = ser.find(username); // gọi hàm bạn viết
            req.setAttribute("student", st);
        }

        req.getRequestDispatcher("student-test.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
