package dev.nlu.portal.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import dev.nlu.portal.exception.DAOException;
import dev.nlu.portal.model.Faculty;

public class FacultyDAO extends BaseDAO implements DAO<Faculty> {

    @Override
    public Optional<Faculty> findById(String id, Connection conn) {
        String sql = "SELECT id, code, name, establishedDate FROM Faculty WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToFaculty(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DAOException("Find Faculty by id failed", e);
        }
    }

    public Optional<Faculty> findByCode(String code, Connection conn) {
        String sql = "SELECT id, code, name, establishedDate FROM Faculty WHERE code = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, code);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToFaculty(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DAOException("Find Faculty by code failed", e);
        }
    }

    @Override
    public List<Faculty> findAll(Connection conn) {
        List<Faculty> faculties = new ArrayList<>();
        String sql = "SELECT id, code, name, establishedDate FROM Faculty";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                faculties.add(mapResultSetToFaculty(rs));
            }
            return faculties;
        } catch (SQLException e) {
            throw new DAOException("Find all Faculties failed", e);
        }
    }

    @Override
    public void insert(Faculty faculty, Connection conn) {
        if (faculty.getId() == null) {
            faculty.setId(UUID.randomUUID().toString());
        }

        String sql = "INSERT INTO Faculty (id, code, name) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, faculty.getId());
            ps.setString(2, faculty.getCode());
            ps.setString(3, faculty.getName());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Insert Faculty failed", e);
        }
    }

    @Override
    public void update(Faculty faculty, Connection conn) {
        String sql = "UPDATE Faculty SET code = ?, name = ?, establishedDate = ? WHERE id = ?";
        try {
            Date establishedDate = faculty.getEstablishedDate() != null
                    ? Date.valueOf(faculty.getEstablishedDate())
                    : null;

            int affected = executeUpdate(
                    conn, sql,
                    faculty.getCode(),
                    faculty.getName(),
                    establishedDate,
                    faculty.getId()
            );

            if (affected == 0) {
                throw new DAOException("Update Faculty failed: faculty not found");
            }
        } catch (SQLException e) {
            throw new DAOException("Update Faculty failed", e);
        }
    }

    @Override
    public void delete(String id, Connection conn) {
        String sql = "DELETE FROM Faculty WHERE id = ?";
        try {
            int affected = executeUpdate(conn, sql, id);
            if (affected == 0) {
                throw new DAOException("Delete Faculty failed: faculty not found");
            }
        } catch (SQLException e) {
            throw new DAOException("Delete Faculty failed", e);
        }
    }

    private Faculty mapResultSetToFaculty(ResultSet rs) throws SQLException {
        Date dbDate = rs.getDate("establishedDate");
        LocalDate establishedDate = (dbDate != null) ? dbDate.toLocalDate() : null;

        return Faculty.builder()
                .id(rs.getString("id"))
                .code(rs.getString("code"))
                .name(rs.getString("name"))
                .establishedDate(establishedDate)
                .build();
    }
}