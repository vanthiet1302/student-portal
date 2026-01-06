package dev.nlu.portal.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.nlu.portal.exception.DAOException;
import dev.nlu.portal.model.Lecturer;

public class LecturerDAO extends BaseDAO implements DAO<Lecturer> {

    public Optional<Lecturer> findById(String userId, Connection conn) {
        String sql = """
            SELECT userId, birthYear, gender, identityCard,
                   academicRank, degree, specialization, position,
                   department, workAgency, agencyAddress,
                   phoneFixed, fax, phoneMobile,
                   bankAccountNumber, bankName, bankBranch
            FROM Lecturers
            WHERE userId = ?
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToLecturer(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DAOException("Find Lecturer by userId failed", e);
        }
    }

    public List<Lecturer> findAll(Connection conn) {
        List<Lecturer> lecturers = new ArrayList<>();

        String sql = """
            SELECT userId, birthYear, gender, identityCard,
                   academicRank, degree, specialization, position,
                   department, workAgency, agencyAddress,
                   phoneFixed, fax, phoneMobile,
                   bankAccountNumber, bankName, bankBranch
            FROM Lecturers
            ORDER BY userId
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lecturers.add(mapResultSetToLecturer(rs));
            }

            return lecturers;
        } catch (SQLException e) {
            throw new DAOException("Find all Lecturers failed", e);
        }
    }
    public List<Lecturer> findAllPaginated(
            int page,
            int pageSize,
            Connection conn
    ) {
        if (page < 1 || pageSize <= 0) {
            throw new DAOException("Invalid pagination parameters");
        }

        int offset = (page - 1) * pageSize;

        String sql = """
            SELECT userId, birthYear, gender, identityCard,
                   academicRank, degree, specialization, position,
                   department, workAgency, agencyAddress,
                   phoneFixed, fax, phoneMobile,
                   bankAccountNumber, bankName, bankBranch
            FROM Lecturers
            ORDER BY userId
            OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
        """;

        List<Lecturer> lecturers = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, offset);
            ps.setInt(2, pageSize);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lecturers.add(mapResultSetToLecturer(rs));
                }
            }

            return lecturers;

        } catch (SQLException e) {
            throw new DAOException("Find paginated Lecturers failed", e);
        }
    }

    public int countAll(Connection conn) {
        String sql = "SELECT COUNT(*) FROM Lecturers";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            throw new DAOException("Count Lecturers failed", e);
        }
    }

    public void insert(Lecturer lecturer, Connection conn) {
        String sql = """
            INSERT INTO Lecturers (
                userId, birthYear, gender, identityCard,
                academicRank, degree, specialization, position,
                department, workAgency, agencyAddress,
                phoneFixed, fax, phoneMobile,
                bankAccountNumber, bankName, bankBranch
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try {
            int affected = executeUpdate(
                conn, sql,
                lecturer.getUserId(),
                lecturer.getBirthYear(),
                lecturer.getGender(),
                lecturer.getIdentityCard(),
                lecturer.getAcademicRank(),
                lecturer.getDegree(),
                lecturer.getSpecialization(),
                lecturer.getPosition(),
                lecturer.getDepartment(),
                lecturer.getWorkAgency(),
                lecturer.getAgencyAddress(),
                lecturer.getPhoneFixed(),
                lecturer.getFax(),
                lecturer.getPhoneMobile(),
                lecturer.getBankAccountNumber(),
                lecturer.getBankName(),
                lecturer.getBankBranch()
            );

            if (affected == 0) {
                throw new DAOException("Insert Lecturer failed");
            }

        } catch (SQLException e) {
            throw new DAOException("Insert Lecturer failed", e);
        }
    }

    public void update(Lecturer lecturer, Connection conn) {
        String sql = """
            UPDATE Lecturers
            SET birthYear = ?,
                gender = ?,
                identityCard = ?,
                academicRank = ?,
                degree = ?,
                specialization = ?,
                position = ?,
                department = ?,
                workAgency = ?,
                agencyAddress = ?,
                phoneFixed = ?,
                fax = ?,
                phoneMobile = ?,
                bankAccountNumber = ?,
                bankName = ?,
                bankBranch = ?
            WHERE userId = ?
        """;

        try {
            int affected = executeUpdate(
                conn, sql,
                lecturer.getBirthYear(),
                lecturer.getGender(),
                lecturer.getIdentityCard(),
                lecturer.getAcademicRank(),
                lecturer.getDegree(),
                lecturer.getSpecialization(),
                lecturer.getPosition(),
                lecturer.getDepartment(),
                lecturer.getWorkAgency(),
                lecturer.getAgencyAddress(),
                lecturer.getPhoneFixed(),
                lecturer.getFax(),
                lecturer.getPhoneMobile(),
                lecturer.getBankAccountNumber(),
                lecturer.getBankName(),
                lecturer.getBankBranch(),
                lecturer.getUserId()
            );

            if (affected == 0) {
                throw new DAOException("Update Lecturer failed: lecturer not found");
            }

        } catch (SQLException e) {
            throw new DAOException("Update Lecturer failed", e);
        }
    }

    public void delete(String userId, Connection conn) {
        String sql = "DELETE FROM Lecturers WHERE userId = ?";

        try {
            int affected = executeUpdate(conn, sql, userId);

            if (affected == 0) {
                throw new DAOException("Delete Lecturer failed: lecturer not found");
            }

        } catch (SQLException e) {
            throw new DAOException("Delete Lecturer failed", e);
        }
    }

    private Lecturer mapResultSetToLecturer(ResultSet rs) throws SQLException {
        return Lecturer.builder()
            .userId(rs.getString("userId"))
            .birthYear(rs.getInt("birthYear"))
            .gender(rs.getString("gender"))
            .identityCard(rs.getString("identityCard"))
            .academicRank(rs.getString("academicRank"))
            .degree(rs.getString("degree"))
            .specialization(rs.getString("specialization"))
            .position(rs.getString("position"))
            .department(rs.getString("department"))
            .workAgency(rs.getString("workAgency"))
            .agencyAddress(rs.getString("agencyAddress"))
            .phoneFixed(rs.getString("phoneFixed"))
            .fax(rs.getString("fax"))
            .phoneMobile(rs.getString("phoneMobile"))
            .bankAccountNumber(rs.getString("bankAccountNumber"))
            .bankName(rs.getString("bankName"))
            .bankBranch(rs.getString("bankBranch"))
            .build();
    }
}
