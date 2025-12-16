package dev.nlu.portal.servlet;

import dev.nlu.portal.utils.DBUtil;
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

@WebServlet(urlPatterns = {"/loadUsers"})
public class LoadUsersServlet extends HttpServlet {
    private Connection connection = DBUtil.getConnection();

    public LoadUsersServlet() throws SQLException {
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String sql = "select * from users";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Long> ids = new ArrayList<>();
        List<String> emails = new ArrayList<>();
        List<String> firstNames = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getLong("userId"));
            emails.add(rs.getString("primaryEmail"));
            firstNames.add(rs.getString("firstName"));
        }
        session.setAttribute("userId", ids);
        session.setAttribute("primaryEmail", emails);
        session.setAttribute("firstName", firstNames);
        req.getRequestDispatcher("loadUsers.jsp").forward(req, resp);
        connection.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
