package dev.nlu.portal.dao;

import dev.nlu.portal.model.Semester;
import dev.nlu.portal.utils.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SemesterDAOImpl implements DAO<Semester> {
    Connection conn;

    @Override
    public Semester findById(Long id) {
        String sql = "SELECT * FROM semesters WHERE id = ?";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return mapRowToSemester(rs);
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
    public List<Semester> findAll() {
        String sql = "SELECT * FROM semesters";
        List<Semester> semesters = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    semesters.add(mapRowToSemester(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtil.close(conn);
            }
        }
        return semesters;
    }

    @Override
    public void save(Semester semester) {
        String sql = "INSERT INTO semesters (semester_code, name, start_date, end_date, current) VALUES (?, ?, ?, ?, ?)";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, semester.getSemesterCode());
                ps.setString(2, semester.getName());
                ps.setDate(3, Date.valueOf(semester.getStartDate()));
                ps.setDate(4, Date.valueOf(semester.getEndDate()));
                ps.setBoolean(5, semester.isCurrent());
                ps.executeUpdate();

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        semester.setId(generatedKeys.getLong(1));
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
    public void update(Semester semester) {
        String sql = "UPDATE semesters SET semester_code = ?, name = ?, start_date = ?, end_date = ?, current = ? WHERE id = ?";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, semester.getSemesterCode());
                ps.setString(2, semester.getName());
                ps.setDate(3, Date.valueOf(semester.getStartDate()));
                ps.setDate(4, Date.valueOf(semester.getEndDate()));
                ps.setBoolean(5, semester.isCurrent());
                ps.setLong(6, semester.getId());
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
        String sql = "DELETE FROM semesters WHERE id = ?";
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

    private Semester mapRowToSemester(ResultSet rs) throws SQLException {
        Semester s = new Semester();
        s.setId(rs.getLong("id"));
        s.setSemesterCode(rs.getString("semester_code"));
        s.setName(rs.getString("name"));
        Date start = rs.getDate("start_date");
        Date end = rs.getDate("end_date");
        s.setStartDate(start != null ? start.toLocalDate() : null);
        s.setEndDate(end != null ? end.toLocalDate() : null);
        s.setCurrent(rs.getBoolean("current"));
        return s;
    }
}
