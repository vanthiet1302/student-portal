package dev.nlu.portal.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import dev.nlu.portal.exception.DAOException;
import dev.nlu.portal.model.AcademicClass;

public class AcademicClassDAO extends BaseDAO implements DAO<AcademicClass> {

    @Override
    public Optional<AcademicClass> findById(String id, Connection conn) {
        String sql = "SELECT id, code, name, facultyId, advisorId, nienKhoa FROM AcademicClass WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToClass(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DAOException("Find AcademicClass by id failed", e);
        }
    }

    public List<AcademicClass> findByFacultyId(String facultyId, Connection conn) {
        List<AcademicClass> classes = new ArrayList<>();
        String sql = "SELECT id, code, name, facultyId, advisorId, nienKhoa FROM AcademicClass WHERE facultyId = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, facultyId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    classes.add(mapResultSetToClass(rs));
                }
            }
            return classes;
        } catch (SQLException e) {
            throw new DAOException("Find AcademicClass by facultyId failed", e);
        }
    }

    @Override
    public List<AcademicClass> findAll(Connection conn) {
        List<AcademicClass> classes = new ArrayList<>();
        String sql = "SELECT id, code, name, facultyId, advisorId, nienKhoa FROM AcademicClass";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                classes.add(mapResultSetToClass(rs));
            }
            return classes;
        } catch (SQLException e) {
            throw new DAOException("Find all AcademicClasses failed", e);
        }
    }

    @Override
    public void insert(AcademicClass clazz, Connection conn) {
        if (clazz.getId() == null) {
            clazz.setId(UUID.randomUUID().toString());
        }

        String sql = """
            INSERT INTO AcademicClass (id, code, name, facultyId, advisorId, nienKhoa)
            VALUES (?, ?, ?, ?, ?, ?)
        """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, clazz.getId());
            ps.setString(2, clazz.getCode());
            ps.setString(3, clazz.getName());
            ps.setString(4, clazz.getFacultyId());
            ps.setString(5, clazz.getAdvisorId());
            ps.setString(6, clazz.getNienKhoa());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Insert AcademicClass failed", e);
        }
    }

    @Override
    public void update(AcademicClass clazz, Connection conn) {
        String sql = """
            UPDATE AcademicClass 
            SET code = ?, name = ?, facultyId = ?, advisorId = ?, nienKhoa = ? 
            WHERE id = ?
        """;
        try {
            int affected = executeUpdate(
                    conn, sql,
                    clazz.getCode(),
                    clazz.getName(),
                    clazz.getFacultyId(),
                    clazz.getAdvisorId(),
                    clazz.getNienKhoa(),
                    clazz.getId()
            );

            if (affected == 0) {
                throw new DAOException("Update AcademicClass failed: class not found");
            }
        } catch (SQLException e) {
            throw new DAOException("Update AcademicClass failed", e);
        }
    }

    @Override
    public void delete(String id, Connection conn) {
        String sql = "DELETE FROM AcademicClass WHERE id = ?";
        try {
            int affected = executeUpdate(conn, sql, id);
            if (affected == 0) {
                throw new DAOException("Delete AcademicClass failed: class not found");
            }
        } catch (SQLException e) {
            throw new DAOException("Delete AcademicClass failed", e);
        }
    }

    private AcademicClass mapResultSetToClass(ResultSet rs) throws SQLException {
        return AcademicClass.builder()
                .id(rs.getString("id"))
                .code(rs.getString("code"))
                .name(rs.getString("name"))
                .facultyId(rs.getString("facultyId"))
                .advisorId(rs.getString("advisorId"))
                .nienKhoa(rs.getString("nienKhoa"))
                .build();
    }
}