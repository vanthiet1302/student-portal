package dev.nlu.portal.dao;

import dev.nlu.portal.model.Class;
import dev.nlu.portal.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassDAOImpl implements DAO<Class> {
    Connection conn;

    @Override
    public Class findById(Long id) {
        String sql = "SELECT * FROM classes WHERE id = ?";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return mapRowToClass(rs);
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
    public List<Class> findAll() {
        String sql = "SELECT * FROM classes";
        List<Class> classes = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    classes.add(mapRowToClass(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtil.close(conn);
            }
        }
        return classes;
    }

    @Override
    public void save(Class c) {
        String sql = "INSERT INTO classes (class_code, course_id, teacher_id, semester_id, max_students, current_students, schedule, week_range) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, c.getClassCode());
                ps.setLong(2, c.getCourseId());
                ps.setLong(3, c.getTeacherId());
                ps.setLong(4, c.getSemesterId());
                ps.setInt(5, c.getMaxStudents());
                ps.setInt(6, c.getCurrentStudents());
                ps.setString(7, c.getSchedule());
                ps.setString(8, c.getWeekRange());
                ps.executeUpdate();

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        c.setId(generatedKeys.getLong(1));
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
    public void update(Class c) {
        String sql = "UPDATE classes SET class_code = ?, course_id = ?, teacher_id = ?, semester_id = ?, max_students = ?, current_students = ?, schedule = ?, week_range = ? WHERE id = ?";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, c.getClassCode());
                ps.setLong(2, c.getCourseId());
                ps.setLong(3, c.getTeacherId());
                ps.setLong(4, c.getSemesterId());
                ps.setInt(5, c.getMaxStudents());
                ps.setInt(6, c.getCurrentStudents());
                ps.setString(7, c.getSchedule());
                ps.setString(8, c.getWeekRange());
                ps.setLong(9, c.getId());
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
        String sql = "DELETE FROM classes WHERE id = ?";
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

    private Class mapRowToClass(ResultSet rs) throws SQLException {
        Class c = new Class();
        c.setId(rs.getLong("id"));
        c.setClassCode(rs.getString("class_code"));
        c.setCourseId(rs.getLong("course_id"));
        c.setTeacherId(rs.getLong("teacher_id"));
        c.setSemesterId(rs.getLong("semester_id"));
        c.setMaxStudents(rs.getInt("max_students"));
        c.setCurrentStudents(rs.getInt("current_students"));
        c.setSchedule(rs.getString("schedule"));
        c.setWeekRange(rs.getString("week_range"));
        return c;
    }
}
