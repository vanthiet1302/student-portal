package dev.nlu.portal.dao;

import dev.nlu.portal.exception.BusinessException;
import dev.nlu.portal.model.Course;
import dev.nlu.portal.utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO extends BaseDAO implements DAO<Course> {
    @Override
    public Course findById(String id) {
        String sql = "SELECT * FROM Courses WHERE id = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCourse(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new BusinessException("Find by id Course failed.", e);
        }
        return null;
    }

    @Override
    public List<Course> findAll() {
        String sql = "SELECT * FROM Courses";
        try (Connection conn = DatabaseUtils.getConnection();
             var pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            List<Course> courses = new ArrayList<>();
            while (rs.next()) {
                courses.add(mapResultSetToCourse(rs));
            }
            return courses;
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new BusinessException("Find all Course failed.", e);
        }
    }

    @Override
    public boolean insert(Course course, Connection conn) {
        String sql = "INSERT INTO Courses (code, name, soTinChi, lyThuyet, thucHanh, url) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            String id = executeInsert(conn, sql, course.getCode(), course.getName(),
                    course.getSoTinChi(), course.getLyThuyet(), course.getThucHanh(), course.getUrl());
            if (id != null) {
                course.setId(id);
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new BusinessException("Insert Course failed.", e);
        }
    }

    @Override
    public boolean update(Course course, Connection conn) {
        String sql = "UPDATE Courses SET code = ?, name = ?, soTinChi = ?, lyThuyet = ?, thucHanh = ?, url = ? " +
                "WHERE id = ?";
        try {
            int rows = executeUpdate(conn, sql, course.getCode(), course.getName(),
                    course.getSoTinChi(), course.getLyThuyet(), course.getThucHanh(), course.getUrl(), course.getId());
            return rows > 0;
        } catch (SQLException e) {
            throw new BusinessException("Update Course failed.", e);
        }
    }

    @Override
    public boolean delete(String id, Connection conn) {
        String sql = "DELETE FROM Courses WHERE id = ?";
        try {
            int rows = executeUpdate(conn, sql, id);
            return rows > 0;
        } catch (SQLException e) {
            throw new BusinessException("Delete Course failed.", e);
        }
    }

    private Course mapResultSetToCourse(ResultSet rs) throws SQLException {
        return Course.builder()
                .id(rs.getString("id"))
                .code(rs.getString("code"))
                .name(rs.getNString("name"))
                .soTinChi(rs.getInt("soTinChi"))
                .lyThuyet(rs.getInt("lyThuyet"))
                .thucHanh(rs.getInt("thucHanh"))
                .url(rs.getString("url"))
                .createdAt(rs.getObject("createdAt", LocalDateTime.class))
                .updatedAt(rs.getObject("updatedAt", LocalDateTime.class))
                .build();
    }

}
