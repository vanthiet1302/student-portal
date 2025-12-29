package dev.nlu.portal.dao;

import dev.nlu.portal.model.ClassRegistration;
import dev.nlu.portal.utils.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClassRegistrationDAOImpl implements DAO<ClassRegistration> {
    Connection conn;

    @Override
    public ClassRegistration findById(Long id) {
        String sql = "SELECT * FROM class_registrations WHERE id = ?";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return mapRowToClassRegistration(rs);
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
    public ClassRegistration findByStudentIdAndClassId ( long  studentId, long classId){
        String sql= "SELECT * FORM class_registrations WHERE student_id = ? AND class_id = ? ";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, studentId);
                ps.setLong(2, classId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return mapRowToClassRegistration(rs);
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
    public List<ClassRegistration> findAll() {
        String sql = "SELECT * FROM class_registrations";
        List<ClassRegistration> list = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRowToClassRegistration(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtil.close(conn);
            }
        }
        return list;
    }

    @Override
    public void save(ClassRegistration cr) {
        String sql = "INSERT INTO class_registrations (student_id, class_id, registered_at, status) VALUES (?, ?, ?, ?)";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setLong(1, cr.getStudentId());
                ps.setLong(2, cr.getClassId());
                LocalDateTime dt = cr.getRegisteredAt();
                ps.setTimestamp(3, dt != null ? Timestamp.valueOf(dt) : new Timestamp(System.currentTimeMillis()));
                // status enum not public; set null to defer
                ps.setNull(4, Types.VARCHAR);
                ps.executeUpdate();

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        cr.setId(generatedKeys.getLong(1));
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
    public void update(ClassRegistration cr) {
        String sql = "UPDATE class_registrations SET student_id = ?, class_id = ?, registered_at = ?, status = ? WHERE id = ?";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, cr.getStudentId());
                ps.setLong(2, cr.getClassId());
                LocalDateTime dt = cr.getRegisteredAt();
                ps.setTimestamp(3, dt != null ? Timestamp.valueOf(dt) : null);
                // status enum not public; set null to defer
                ps.setNull(4, Types.VARCHAR);
                ps.setLong(5, cr.getId());
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
        String sql = "DELETE FROM class_registrations WHERE id = ?";
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

    private ClassRegistration mapRowToClassRegistration(ResultSet rs) throws SQLException {
        ClassRegistration cr = new ClassRegistration();
        cr.setId(rs.getLong("id"));
        cr.setStudentId(rs.getLong("student_id"));
        cr.setClassId(rs.getLong("class_id"));
        Timestamp ts = rs.getTimestamp("registered_at");
        cr.setRegisteredAt(ts != null ? ts.toLocalDateTime() : null);
        // cr.setStatus(...); // enum not public, skip mapping
        return cr;
    }
}
