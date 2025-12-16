package dev.nlu.portal.servlet;

import dev.nlu.portal.dao.DAO;
import dev.nlu.portal.dao.StudentDAO;
import dev.nlu.portal.model.Student;
import dev.nlu.portal.utils.DBUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        resp.sendRedirect("loadAllUser.jsp");
    }
}
