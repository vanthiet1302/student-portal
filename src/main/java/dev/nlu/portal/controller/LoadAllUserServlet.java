package dev.nlu.portal.controller;

import dev.nlu.portal.dao.DAO;
import dev.nlu.portal.dao.StudentDAO;
import dev.nlu.portal.model.Student;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/loadAllUser"})
public class LoadAllUserServlet extends HttpServlet {
    DAO studentDAO;

    public LoadAllUserServlet() throws SQLException {
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        studentDAO = new StudentDAO();
        List<Student> lstStudent = studentDAO.findAll();
        HttpSession session = req.getSession(true);
        session.setAttribute("allStudent", lstStudent);
        RequestDispatcher rd = req.getServletContext().getRequestDispatcher("/load-users.jsp");
        rd.forward(req,resp);
    }
}
