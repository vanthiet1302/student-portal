package dev.nlu.portal.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import dev.nlu.portal.exception.DAOException;
import dev.nlu.portal.model.Course;

public class CourseDAO extends BaseDAO implements DAO<Course> {

    @Override
    public Optional<Course> findById(String id, Connection conn) {
        String sql = """
            SELECT id, code, name, soTinChi, lyThuyet, thucHanh, url, createdAt, updatedAt
            FROM Courses
            WHERE id = ?
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToCourse(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DAOException("Find Course by id failed", e);
        }
    }

    public Optional<Course> findByCode(String code, Connection conn) {
        String sql = """
            SELECT id, code, name, soTinChi, lyThuyet, thucHanh, url, createdAt, updatedAt
            FROM Courses
            WHERE code = ?
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, code);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToCourse(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DAOException("Find Course by code failed", e);
        }
    }

    @Override
    public List<Course> findAll(Connection conn) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT id, code, name, soTinChi, lyThuyet, thucHanh, url, createdAt, updatedAt FROM Courses";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                courses.add(mapResultSetToCourse(rs));
            }
            return courses;
        } catch (SQLException e) {
            throw new DAOException("Find all Courses failed", e);
        }
    }

    @Override
    public void insert(Course course, Connection conn) {
        if (course.getId() == null) {
            course.setId(UUID.randomUUID().toString());
        }

        String sql = """
            INSERT INTO Courses (id, code, name, soTinChi, lyThuyet, thucHanh, url)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, course.getId());
            ps.setString(2, course.getCode());
            ps.setString(3, course.getName());
            ps.setInt(4, course.getSoTinChi());
            ps.setInt(5, course.getLyThuyet());
            ps.setInt(6, course.getThucHanh());
            ps.setString(7, course.getUrl());

            int affected = ps.executeUpdate();
            if (affected != 1) {
                throw new DAOException("Insert Course failed, affected rows: " + affected);
            }
        } catch (SQLException e) {
            throw new DAOException("Insert Course failed", e);
        }
    }

    @Override
    public void update(Course course, Connection conn) {
        String sql = """
            UPDATE Courses
            SET code = ?,
                name = ?,
                soTinChi = ?,
                lyThuyet = ?,
                thucHanh = ?,
                url = ?
            WHERE id = ?
        """;

        try {
            int affected = executeUpdate(
                    conn, sql,
                    course.getCode(),
                    course.getName(),
                    course.getSoTinChi(),
                    course.getLyThuyet(),
                    course.getThucHanh(),
                    course.getUrl(),
                    course.getId()
            );

            if (affected == 0) {
                throw new DAOException("Update Course failed: course not found");
            }
        } catch (SQLException e) {
            throw new DAOException("Update Course failed", e);
        }
    }

    @Override
    public void delete(String id, Connection conn) {
        String sql = "DELETE FROM Courses WHERE id = ?";
        try {
            int affected = executeUpdate(conn, sql, id);
            if (affected == 0) {
                throw new DAOException("Delete Course failed: course not found");
            }
        } catch (SQLException e) {
            throw new DAOException("Delete Course failed", e);
        }
    }

    private Course mapResultSetToCourse(ResultSet rs) throws SQLException {
        return Course.builder()
                .id(rs.getString("id"))
                .code(rs.getString("code"))
                .name(rs.getString("name"))
                .soTinChi(rs.getInt("soTinChi"))
                .lyThuyet(rs.getInt("lyThuyet"))
                .thucHanh(rs.getInt("thucHanh"))
                .url(rs.getString("url"))
                .createdAt(rs.getTimestamp("createdAt").toLocalDateTime())
                .updatedAt(rs.getTimestamp("updatedAt").toLocalDateTime())
                .build();
    }
}