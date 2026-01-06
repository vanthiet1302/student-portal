package dev.nlu.portal.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.nlu.portal.exception.DAOException;
import dev.nlu.portal.model.Student;
import dev.nlu.portal.model.Status;

public class StudentDAO extends BaseDAO implements DAO<Student>{

    public Optional<Student> findById(String userId, Connection conn) {
        String sql = """
            SELECT userId, dob, pob, gender, status,
                   phoneNumber, citizenId, nation,
                   religion, nationality, address, classId
            FROM Students
            WHERE userId = ?
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToStudent(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DAOException("Find Student by userId failed", e);
        }
    }

    public List<Student> findAll(Connection conn) {
        List<Student> students = new ArrayList<>();

        String sql = """
            SELECT userId, dob, pob, gender, status,
                   phoneNumber, citizenId, nation,
                   religion, nationality, address, classId
            FROM Students
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                students.add(mapResultSetToStudent(rs));
            }

            return students;
        } catch (SQLException e) {
            throw new DAOException("Find all Students failed", e);
        }
    }

    public void insert(Student student, Connection conn) {
        String sql = """
            INSERT INTO Students (
                userId, dob, pob, gender, status,
                phoneNumber, citizenId, nation,
                religion, nationality, address, classId
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try {
            int affected = executeUpdate(
                conn, sql,
                student.getUserId(),
                student.getDob(),
                student.getPob(),
                student.getGender(),
                student.getStatus().name(),
                student.getPhoneNumber(),
                student.getCitizenId(),
                student.getNation(),
                student.getReligion(),
                student.getNationality(),
                student.getAddress(),
                student.getClassId()
            );

            if (affected == 0) {
                throw new DAOException("Insert Student failed");
            }

        } catch (SQLException e) {
            throw new DAOException("Insert Student failed", e);
        }
    }

    public void update(Student student, Connection conn) {
        String sql = """
            UPDATE Students
            SET dob = ?,
                pob = ?,
                gender = ?,
                status = ?,
                phoneNumber = ?,
                citizenId = ?,
                nation = ?,
                religion = ?,
                nationality = ?,
                address = ?,
                classId = ?
            WHERE userId = ?
        """;

        try {
            int affected = executeUpdate(
                conn, sql,
                student.getDob(),
                student.getPob(),
                student.getGender(),
                student.getStatus().name(),
                student.getPhoneNumber(),
                student.getCitizenId(),
                student.getNation(),
                student.getReligion(),
                student.getNationality(),
                student.getAddress(),
                student.getClassId(),
                student.getUserId()
            );

            if (affected == 0) {
                throw new DAOException("Update Student failed: student not found");
            }

        } catch (SQLException e) {
            throw new DAOException("Update Student failed", e);
        }
    }

    public void delete(String userId, Connection conn) {
        String sql = "DELETE FROM Students WHERE userId = ?";

        try {
            int affected = executeUpdate(conn, sql, userId);

            if (affected == 0) {
                throw new DAOException("Delete Student failed: student not found");
            }

        } catch (SQLException e) {
            throw new DAOException("Delete Student failed", e);
        }
    }

    private Student mapResultSetToStudent(ResultSet rs) throws SQLException {

        Date dobDate = rs.getDate("dob");
        LocalDate dob = dobDate != null ? dobDate.toLocalDate() : null;

        return Student.builder()
            .userId(rs.getString("userId"))
            .dob(dob)
            .pob(rs.getString("pob"))
            .gender(rs.getString("gender"))
            .status(Status.valueOf(rs.getString("status")))
            .phoneNumber(rs.getString("phoneNumber"))
            .citizenId(rs.getString("citizenId"))
            .nation(rs.getString("nation"))
            .religion(rs.getString("religion"))
            .nationality(rs.getString("nationality"))
            .address(rs.getString("address"))
            .classId(rs.getString("classId"))
            .build();
    }
}
