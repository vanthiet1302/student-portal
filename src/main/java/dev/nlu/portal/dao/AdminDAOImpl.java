package dev.nlu.portal.dao;

import dev.nlu.portal.model.Admin;
import dev.nlu.portal.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements DAO<Admin> {
    Connection conn;

    @Override
    public Admin findById(Long id) {
        String sql = "SELECT * FROM admins WHERE id = ?";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return mapRowToAdmin(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtil.close(conn);
            }
        }
        return null;
    }

    @Override
    public List<Admin> findAll() {
        String sql = "SELECT * FROM admins";
        List<Admin> admins = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    admins.add(mapRowToAdmin(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtil.close(conn);
            }
        }
        return admins;
    }

    @Override
    public void save(Admin admin) {
        String sql = "INSERT INTO admins (user_id, full_name, email, phone) VALUES (?, ?, ?, ?)";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setLong(1, admin.getUserId());
                ps.setString(2, admin.getFullName());
                ps.setString(3, admin.getEmail());
                ps.setString(4, admin.getPhone());
                ps.executeUpdate();

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        admin.setId(generatedKeys.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtil.close(conn);
            }
        }
    }

    @Override
    public void update(Admin admin) {
        String sql = "UPDATE admins SET user_id = ?, full_name = ?, email = ?, phone = ? WHERE id = ?";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, admin.getUserId());
                ps.setString(2, admin.getFullName());
                ps.setString(3, admin.getEmail());
                ps.setString(4, admin.getPhone());
                ps.setLong(5, admin.getId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtil.close(conn);
            }
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM admins WHERE id = ?";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtil.close(conn);
            }
        }
    }

    private Admin mapRowToAdmin(ResultSet rs) throws SQLException {
        Admin admin = new Admin();
        admin.setId(rs.getLong("id"));
        admin.setUserId(rs.getLong("user_id"));
        admin.setFullName(rs.getString("full_name"));
        admin.setEmail(rs.getString("email"));
        admin.setPhone(rs.getString("phone"));
        return admin;
    }
}
