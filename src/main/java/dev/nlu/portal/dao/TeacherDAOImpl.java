package dev.nlu.portal.dao;

import dev.nlu.portal.model.Degree;
import dev.nlu.portal.model.Teacher;
import dev.nlu.portal.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements DAO<Teacher> {
    Connection conn;

    @Override
    public Teacher findById(Long id) {
        String sql = "SELECT * FROM teachers WHERE id = ?";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return mapRowToTeacher(rs);
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
    public List<Teacher> findAll() {
        String sql = "SELECT * FROM teachers";
        List<Teacher> teachers = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    teachers.add(mapRowToTeacher(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtil.close(conn);
            }
        }
        return teachers;
    }

    @Override
    public void save(Teacher teacher) {
        String sql = "INSERT INTO teachers (user_id, teacher_code, full_name, email, phone, department, degree) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setLong(1, teacher.getUserId());
                ps.setString(2, teacher.getTeacherCode());
                ps.setString(3, teacher.getFullName());
                ps.setString(4, teacher.getEmail());
                ps.setString(5, teacher.getPhone());
                ps.setString(6, teacher.getDepartment());
                ps.setString(7, teacher.getDegree() != null ? teacher.getDegree().name() : null);
                ps.executeUpdate();

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        teacher.setId(generatedKeys.getLong(1));
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
    public void update(Teacher teacher) {
        String sql = "UPDATE teachers SET user_id = ?, teacher_code = ?, full_name = ?, email = ?, phone = ?, department = ?, degree = ? WHERE id = ?";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, teacher.getUserId());
                ps.setString(2, teacher.getTeacherCode());
                ps.setString(3, teacher.getFullName());
                ps.setString(4, teacher.getEmail());
                ps.setString(5, teacher.getPhone());
                ps.setString(6, teacher.getDepartment());
                ps.setString(7, teacher.getDegree() != null ? teacher.getDegree().name() : null);
                ps.setLong(8, teacher.getId());
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
        String sql = "DELETE FROM teachers WHERE id = ?";
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

    private Teacher mapRowToTeacher(ResultSet rs) throws SQLException {
        Teacher t = new Teacher();
        t.setId(rs.getLong("id"));
        t.setUserId(rs.getLong("user_id"));
        t.setTeacherCode(rs.getString("teacher_code"));
        t.setFullName(rs.getString("full_name"));
        t.setEmail(rs.getString("email"));
        t.setPhone(rs.getString("phone"));
        t.setDepartment(rs.getString("department"));
        String degreeStr = rs.getString("degree");
        t.setDegree(degreeStr != null ? Degree.valueOf(degreeStr) : null);
        return t;
    }
}
