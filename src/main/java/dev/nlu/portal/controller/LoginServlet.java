package dev.nlu.portal.controller;

import java.io.IOException;
import java.io.PrintWriter;

import dev.nlu.portal.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
    StudentService studentService = new StudentService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        boolean isValid = studentService.existsByUsername(username);
        if (isValid) {
            boolean checkPassword = studentService.checkPassword(username, password);
            if (checkPassword) {
                resp.setContentType("text/html;charset=utf-8");
                PrintWriter write = resp.getWriter();
                write.println("<h2>Đăng nhập thành công</h2>");
                write.println("<a href='load-users.jsp'>Đi đến trang danh sách sinh viên</a>");
            } else {
                req.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
               req.getRequestDispatcher("/WEB-INF/auth/login.jsp").forward(req, resp);

            }
        } else {
            req.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
            req.getRequestDispatcher("/WEB-INF/auth/login.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
