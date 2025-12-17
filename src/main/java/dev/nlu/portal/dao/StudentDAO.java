/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.nlu.portal.dao;

import dev.nlu.portal.model.Student;
import dev.nlu.portal.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.time.LocalDateTime;

public class StudentDAO implements DAO<Student> {
    private Connection connection;

    public StudentDAO() {
    }


    @Override
    public int save(Student t) {
        if (t == null) return 0;
        if (t.getId() == null || t.getId().trim().isEmpty()) {
            t.setId(UUID.randomUUID().toString().substring(0, 5));
        }
        String sql = "INSERT INTO students (id, username, primary_email, hash_password, first_name, " +
                "last_name, dob, is_male, status, phone, citizen_id, nation, religion, pob, nationality, " +
                "address, create_at, update_at, class_id, avatar_url) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int result = 0;
        try {
            connection = DBUtil.getConnection();
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                int i = 1;
                ps.setString(i++, t.getId());
                ps.setString(i++, t.getUsername());
                ps.setString(i++, t.getPrimaryEmail());
                ps.setString(i++, t.getHashPassword());
                ps.setString(i++, t.getFirstName());
                ps.setString(i++, t.getLastName());
                if (t.getDob() != null) ps.setDate(i++, new java.sql.Date(t.getDob().getTime())); else ps.setDate(i++, null);
                ps.setBoolean(i++, t.isMale());
                ps.setString(i++, t.getStatus());
                ps.setString(i++, t.getPhone());
                ps.setString(i++, t.getCitizenId());
                ps.setString(i++, t.getNation());
                ps.setString(i++, t.getReligion());
                ps.setString(i++, t.getPob());
                ps.setString(i++, t.getNationality());
                ps.setString(i++, t.getAddress());
                if (t.getCreateAt() != null) ps.setTimestamp(i++, Timestamp.valueOf(t.getCreateAt()));
                else ps.setTimestamp(i++, null);
                if (t.getUpdateAt() != null) ps.setTimestamp(i++, Timestamp.valueOf(t.getUpdateAt()));
                else ps.setTimestamp(i++, null);
                ps.setString(i++, t.getClassId());
                ps.setString(i++, t.getAvatarUrl());

                result = ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Lỗi mở Connection: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DBUtil.close(connection);
        }
        return result;
    }

    @Override
    public int update(String id, Map<String, Object> updates) {
        if (id == null || id.trim().isEmpty() || updates == null || updates.isEmpty()) return 0;
        StringBuilder sb = new StringBuilder("UPDATE students SET ");
        List<Object> params = new ArrayList<>();
        for (String key : updates.keySet()) {
            sb.append(key).append(" = ?, ");
            params.add(updates.get(key));
        }
        sb.setLength(sb.length() - 2); // remove trailing comma+space
        sb.append(" WHERE id = ?");
        int result = 0;
        try {
            connection = DBUtil.getConnection();
            try (PreparedStatement ps = connection.prepareStatement(sb.toString())) {
                int i = 1;
                for (Object val : params) {
                    if (val == null) {
                        ps.setObject(i++, null);
                    } else if (val instanceof LocalDateTime) {
                        ps.setTimestamp(i++, Timestamp.valueOf((LocalDateTime) val));
                    } else if (val instanceof java.util.Date) {
                        ps.setDate(i++, new java.sql.Date(((java.util.Date) val).getTime()));
                    } else if (val instanceof Boolean) {
                        ps.setBoolean(i++, (Boolean) val);
                    } else if (val instanceof Integer) {
                        ps.setInt(i++, (Integer) val);
                    } else {
                        ps.setObject(i++, val);
                    }
                }
                ps.setString(i, id);
                result = ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Lỗi mở Connection: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DBUtil.close(connection);
        }
        return result;
    }

    @Override
    public int delete(Student st) {
        if (st == null || st.getId() == null) return 0;
        String sql = "DELETE FROM students WHERE id = ?";
        int result = 0;
        try {
            connection = DBUtil.getConnection();
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, st.getId());
                result = ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Lỗi mở Connection: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DBUtil.close(connection);
        }
        return result;
    }

    @Override
    public Student find(String id) {
        if (id == null) return null;
        Student result = null;
        String sql = "SELECT * FROM students WHERE id = ?";
        try {
            connection = DBUtil.getConnection();
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        result = mapResultSetToStudent(rs);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi mở Connection: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DBUtil.close(connection);
        }
        return result;
    }

    @Override
    public List<Student> findAll() {
        List<Student> result = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try {
            connection = DBUtil.getConnection();
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        result.add(mapResultSetToStudent(rs));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi mở Connection: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DBUtil.close(connection);
        }
        return result;
    }

    public Student findByUsername(String username) {
        if (username == null) return null;
        Student result = null;
        String sql = "SELECT * FROM students WHERE username = ?";
        try {
            connection = DBUtil.getConnection();
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        result = mapResultSetToStudent(rs);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi mở Connection: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DBUtil.close(connection);
        }
        return result;
    }

    private Student mapResultSetToStudent(ResultSet rs) throws SQLException {
        Student s = new Student();
        s.setId(rs.getString("id"));
        s.setUsername(rs.getString("username"));
        s.setPrimaryEmail(rs.getString("primary_email"));
        s.setHashPassword(rs.getString("hash_password"));
        s.setFirstName(rs.getString("first_name"));
        s.setLastName(rs.getString("last_name"));
        java.sql.Date dob = rs.getDate("dob");
        if (dob != null) s.setDob(new java.util.Date(dob.getTime()));
        // Lombok/builder generates setter setMale for boolean field isMale
        try {
            s.setMale(rs.getBoolean("is_male"));
        } catch (SQLException ignored) {
        }
        s.setStatus(rs.getString("status"));
        s.setPhone(rs.getString("phone"));
        s.setCitizenId(rs.getString("citizen_id"));
        s.setNation(rs.getString("nation"));
        s.setReligion(rs.getString("religion"));
        s.setPob(rs.getString("pob"));
        s.setNationality(rs.getString("nationality"));
        s.setAddress(rs.getString("address"));
        Timestamp t1 = rs.getTimestamp("create_at");
        if (t1 != null) s.setCreateAt(t1.toLocalDateTime());
        Timestamp t2 = rs.getTimestamp("update_at");
        if (t2 != null) s.setUpdateAt(t2.toLocalDateTime());
        s.setClassId(rs.getString("class_id"));
        s.setAvatarUrl(rs.getString("avatar_url"));
        return s;
    }
}
