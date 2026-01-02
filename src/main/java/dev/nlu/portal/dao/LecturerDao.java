package dev.nlu.portal.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dev.nlu.portal.exception.DAOException;
import dev.nlu.portal.model.Lecturer;
import dev.nlu.portal.utils.DatabaseUtils;

public class LecturerDao extends BaseDAO implements DAO<Lecturer> {


    @Override
    public Lecturer findById(String id) {
        String sql = "SELECT * FROM Lecturers l JOIN Users u ON l.userId = u.id WHERE l.userId = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToLecturer(rs);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Find by Id Lecturer failed: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<Lecturer> findAll() {
        List<Lecturer> list = new ArrayList<>();
        String sql = "SELECT * FROM Lecturers l JOIN Users u ON l.userId = u.id";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToLecturer(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("Find all Lecturer failed: " + e.getMessage() , e);
        }
        return list;
    }

    @Override
    public boolean insert(Lecturer t, Connection conn) {
        String sql = "INSERT INTO Lecturers (userId, fullName, birthYear, gender, identityCard, academicRank, degree, " +
                "specialization, position, department, workAgency, agencyAddress, phoneFixed, fax, " +
                "phoneMobile, bankAccountNumber, bankName, bankBranch) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            String id = executeInsert(conn, sql,
                    t.getUserId(),
                    t.getFullName(),
                    t.getBirthYear(),
                    t.getGender(),
                    t.getIdentityCard(),
                    t.getAcademicRank(),
                    t.getDegree(),
                    t.getSpecialization(),
                    t.getPosition(),
                    t.getDepartment(),
                    t.getWorkAgency(),
                    t.getAgencyAddress(),
                    t.getPhoneFixed(),
                    t.getFax(),
                    t.getPhoneMobile(),
                    t.getBankAccountNumber(),
                    t.getBankName(),
                    t.getBankBranch()
            );
            if (id != null) {
                t.setUserId(id);
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new DAOException("Insert Lecturer failed: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean update(Lecturer t, Connection conn) {
        String sql = "UPDATE Lecturers SET fullName = ?, birthYear = ?, gender = ?, identityCard = ?, " +
                "academicRank = ?, degree = ?, specialization = ?, position = ?, department = ?, " +
                "phoneFixed = ?, fax = ?, emailWork = ?, " +
                "emailPersonal = ?, phoneMobile = ?, bankAccountNumber = ?, bankName = ?, bankBranch = ? " +
                "WHERE userId = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setNString(1, t.getFullName());
            ps.setInt(2, t.getBirthYear());
            ps.setNString(3, t.getGender());
            ps.setString(4, t.getIdentityCard());
            ps.setNString(5, t.getAcademicRank());
            ps.setNString(6, t.getDegree());
            ps.setNString(7, t.getSpecialization());
            ps.setNString(8, t.getPosition());
            ps.setNString(9, t.getDepartment());
            ps.setNString(10, t.getWorkAgency());
            ps.setNString(11, t.getAgencyAddress());
            ps.setString(12, t.getPhoneFixed());
            ps.setString(13, t.getFax());
            ps.setString(14, t.getPhoneMobile());
            ps.setString(15, t.getBankAccountNumber());
            ps.setNString(16, t.getBankName());
            ps.setNString(17, t.getBankBranch());

            ps.setString(18, t.getUserId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Update Lecturer failed: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(String id, Connection conn) {
        String sql = "DELETE FROM Users WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Delete Lecturer failed: " + e.getMessage(), e);
        }
    }

    private Lecturer mapResultSetToLecturer(ResultSet rs) throws SQLException {
        return Lecturer.builder()
                .userId(rs.getString("userId"))
                .fullName(rs.getNString("fullName"))
                .birthYear(rs.getInt("birthYear"))
                .gender(rs.getNString("gender"))
                .identityCard(rs.getString("identityCard"))
                .academicRank(rs.getNString("academicRank"))
                .degree(rs.getNString("degree"))
                .specialization(rs.getNString("specialization"))
                .position(rs.getNString("position"))
                .department(rs.getNString("department"))
                .workAgency(rs.getNString("workAgency"))
                .agencyAddress(rs.getNString("agencyAddress"))
                .phoneFixed(rs.getString("phoneFixed"))
                .fax(rs.getString("fax"))
                .phoneMobile(rs.getString("phoneMobile"))
                .bankAccountNumber(rs.getString("bankAccountNumber"))
                .bankName(rs.getNString("bankName"))
                .bankBranch(rs.getNString("bankBranch"))
                .build();
    }
}
