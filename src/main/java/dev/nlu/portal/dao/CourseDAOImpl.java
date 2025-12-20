package dev.nlu.portal.dao;

import dev.nlu.portal.model.Course;
import dev.nlu.portal.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements DAO<Course> {
    Connection conn;

    @Override
    public Course findById(Long id) {
        String sql = "SELECT * FROM courses WHERE id = ?";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return mapRowToCourse(rs);
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
    public List<Course> findAll() {
        String sql = "SELECT * FROM courses";
        List<Course> courses = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    courses.add(mapRowToCourse(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtil.close(conn);
            }
        }
        return courses;
    }

    @Override
    public void save(Course course) {
        String sql = "INSERT INTO courses (course_code, name, credits, department) VALUES (?, ?, ?, ?)";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, course.getCourseCode());
                ps.setString(2, course.getName());
                ps.setInt(3, course.getCredits());
                ps.setString(4, course.getDepartment());
                ps.executeUpdate();

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        course.setId(generatedKeys.getLong(1));
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
    public void update(Course course) {
        String sql = "UPDATE courses SET course_code = ?, name = ?, credits = ?, department = ? WHERE id = ?";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, course.getCourseCode());
                ps.setString(2, course.getName());
                ps.setInt(3, course.getCredits());
                ps.setString(4, course.getDepartment());
                ps.setLong(5, course.getId());
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
        String sql = "DELETE FROM courses WHERE id = ?";
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

    private Course mapRowToCourse(ResultSet rs) throws SQLException {
        Course c = new Course();
        c.setId(rs.getLong("id"));
        c.setCourseCode(rs.getString("course_code"));
        c.setName(rs.getString("name"));
        c.setCredits(rs.getInt("credits"));
        c.setDepartment(rs.getString("department"));
        return c;
    }
}
