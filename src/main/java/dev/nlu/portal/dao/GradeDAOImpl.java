package dev.nlu.portal.dao;

import dev.nlu.portal.model.Grade;
import dev.nlu.portal.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeDAOImpl implements DAO<Grade> {
    Connection conn;

    @Override
    public Grade findById(Long id) {
        String sql = "SELECT * FROM grades WHERE id = ?";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return mapRowToGrade(rs);
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
    public List<Grade> findAll() {
        String sql = "SELECT * FROM grades";
        List<Grade> grades = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    grades.add(mapRowToGrade(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtil.close(conn);
            }
        }
        return grades;
    }

    @Override
    public void save(Grade grade) {
        String sql = "INSERT INTO grades (registration_id, midterm_score, final_score, overall_score, letter_grade) VALUES (?, ?, ?, ?, ?)";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setLong(1, grade.getRegistrationId());
                setNullableDouble(ps, 2, grade.getMidtermScore());
                setNullableDouble(ps, 3, grade.getFinalScore());
                setNullableDouble(ps, 4, grade.getOverallScore());
                ps.setString(5, grade.getLetterGrade());
                ps.executeUpdate();

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        grade.setId(generatedKeys.getLong(1));
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
    public void update(Grade grade) {
        String sql = "UPDATE grades SET registration_id = ?, midterm_score = ?, final_score = ?, overall_score = ?, letter_grade = ? WHERE id = ?";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, grade.getRegistrationId());
                setNullableDouble(ps, 2, grade.getMidtermScore());
                setNullableDouble(ps, 3, grade.getFinalScore());
                setNullableDouble(ps, 4, grade.getOverallScore());
                ps.setString(5, grade.getLetterGrade());
                ps.setLong(6, grade.getId());
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
        String sql = "DELETE FROM grades WHERE id = ?";
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

    private Grade mapRowToGrade(ResultSet rs) throws SQLException {
        Grade g = new Grade();
        g.setId(rs.getLong("id"));
        g.setRegistrationId(rs.getLong("registration_id"));
        g.setMidtermScore(getNullableDouble(rs, "midterm_score"));
        g.setFinalScore(getNullableDouble(rs, "final_score"));
        g.setOverallScore(getNullableDouble(rs, "overall_score"));
        g.setLetterGrade(rs.getString("letter_grade"));
        return g;
    }

    private void setNullableDouble(PreparedStatement ps, int index, Double value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.DECIMAL);
        } else {
            ps.setDouble(index, value);
        }
    }

    private Double getNullableDouble(ResultSet rs, String column) throws SQLException {
        double v = rs.getDouble(column);
        return rs.wasNull() ? null : v;
    }
}
