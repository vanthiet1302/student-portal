/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.nlu.portal.dao;

import dev.nlu.portal.model.Student;
import dev.nlu.portal.utils.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentDAO implements DAO<Student> {
    private Connection connection;

    @Override
    public int update(Student student) {
        int rowsUpdated = 0;
        String sql = "UPDATE students SET " +
                "primary_email = ?, first_name = ?, last_name = ?, hash_password = ?, " +
                "dob = ?, is_male = ?, status = ?, phone = ?, citizen_id = ?, nation = ?, religion = ?, " +
                "pob = ?, nationality = ?, address = ?, created_at = ?, updated_at = ?, class_id = ?, avatar_url = ? " +
                "WHERE username = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            int idx = 1;
            ps.setString(idx++, student.getPrimaryEmail());
            ps.setString(idx++, student.getFirstname());
            ps.setString(idx++, student.getLastname());
            ps.setString(idx++, student.getHashPassword());

            // Chuyển java.util.Date sang java.sql.Date
            ps.setDate(idx++, student.getDob() != null ? new java.sql.Date(student.getDob().getTime()) : null);

            ps.setBoolean(idx++, student.isMale());
            ps.setString(idx++, student.getStatus());
            ps.setString(idx++, student.getPhone());
            ps.setString(idx++, student.getCitizenId());
            ps.setString(idx++, student.getNation());
            ps.setString(idx++, student.getReligion());
            ps.setString(idx++, student.getPob());
            ps.setString(idx++, student.getNationality());
            ps.setString(idx++, student.getAddress());

            // createdAt và updatedAt
            ps.setTimestamp(idx++, student.getCreateAt() != null ? Timestamp.valueOf(student.getCreateAt()) : Timestamp.valueOf(LocalDateTime.now()));
            ps.setTimestamp(idx++, student.getUpdateAt() != null ? Timestamp.valueOf(student.getUpdateAt()) : Timestamp.valueOf(LocalDateTime.now()));

            ps.setString(idx++, student.getClassId());
            ps.setString(idx++, student.getAvatarUrl());
            ps.setString(idx++, student.getUsername());

            rowsUpdated = ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Lỗi update Student: " + e.getMessage());
            e.printStackTrace();
        }

        return rowsUpdated;
    }


    @Override
        public int delete(Student student) {
            int rowsDeleted = 0;
            String sql = "DELETE FROM students WHERE username = ?";

            try (Connection connection = DBUtil.getConnection();
                 PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setString(1, student.getUsername());
                rowsDeleted= ps.executeUpdate();

            } catch (SQLException e) {
                System.err.println("Lỗi DELETE: " + e.getMessage());
                e.printStackTrace();
                return 0;
            }
            return rowsDeleted;
        }


    @Override
    public Student find(String username) {
        Student student = null;
        String sql = "SELECT * FROM students WHERE username = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    student = new Student();
                    student.setUsername(rs.getString("username"));
                    student.setFirstname(rs.getString("first_name"));
                    student.setLastname(rs.getString("last_name"));
                    student.setPrimaryEmail(rs.getString("primary_email"));
                    student.setHashPassword(rs.getString("hash_password"));
                    student.setDob(rs.getDate("dob"));
                    student.setPhone(rs.getString("phone"));
                    student.setStatus(rs.getString("status"));
                    student.setCitizenId(rs.getString("citizen_id"));
                    student.setNation(rs.getString("nation"));
                    student.setReligion(rs.getString("religion"));
                    student.setPob(rs.getString("pob"));
                    student.setNationality(rs.getString("nationality"));
                    student.setAddress(rs.getString("address"));
                    student.setClassId(rs.getString("class_id"));
                    student.setAvatarUrl(rs.getString("avatar_url"));

                    Timestamp createTs = rs.getTimestamp("created_at");
                    if (createTs != null) student.setCreateAt(createTs.toLocalDateTime());

                    Timestamp updateTs = rs.getTimestamp("updated_at");
                    if (updateTs != null) student.setUpdateAt(updateTs.toLocalDateTime());
                }
            }

        } catch (SQLException e) {
            System.err.println("Lỗi findByUsername: " + e.getMessage());
            e.printStackTrace();
        }

        return student;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students "; // tên bảng thống nhất

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Student student = new Student();
                    student = new Student();
                    student.setId(rs.getString("id"));
                    student.setUsername(rs.getString("username"));
                    student.setFirstname(rs.getString("first_name"));
                    student.setLastname(rs.getString("last_name"));
                    student.setPrimaryEmail(rs.getString("primary_email"));
                    student.setHashPassword(rs.getString("hash_password"));
                    student.setDob(rs.getDate("dob"));
                    student.setPhone(rs.getString("phone"));
                    student.setStatus(rs.getString("status"));
                    student.setCitizenId(rs.getString("citizen_id"));
                    student.setNation(rs.getString("nation"));
                    student.setReligion(rs.getString("religion"));
                    student.setPob(rs.getString("pob"));
                    student.setNationality(rs.getString("nationality"));
                    student.setAddress(rs.getString("address"));
                    student.setClassId(rs.getString("class_id"));
                    student.setAvatarUrl(rs.getString("avatar_url"));

                    Timestamp createTs = rs.getTimestamp("created_at");
                    if (createTs != null) student.setCreateAt(createTs.toLocalDateTime());

                    Timestamp updateTs = rs.getTimestamp("updated_at");
                    if (updateTs != null) student.setUpdateAt(updateTs.toLocalDateTime());
                    students.add(student);
                }

            }

        } catch (SQLException e) {
            System.err.println("Lỗi findByUsername: " + e.getMessage());
            e.printStackTrace();
        }
        return students;

    }

    @Override
    public int save(Student student) {
        try {
            connection = DBUtil.getConnection();
            String sql = "INSERT INTO students (id, username, primary_email, first_name, last_name, hash_password, dob, is_male, status, phone, citizen_id, nation, religion, pob, nationality, address, created_at, updated_at, class_id, avatar_url ) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                int idx = 1;
                ps.setString(idx++, student.getId());
                ps.setString(idx++, student.getUsername());
                ps.setString(idx++, student.getPrimaryEmail());
                ps.setString(idx++, student.getFirstname());
                ps.setString(idx++, student.getLastname());
                ps.setString(idx++, student.getHashPassword());
                ps.setDate(idx++, (Date) student.getDob());
                ps.setBoolean(idx++, student.isMale());
                ps.setString(idx++, student.getStatus());
                ps.setString(idx++, student.getPhone());
                ps.setString(idx++, student.getCitizenId());
                ps.setString(idx++, student.getNation());
                ps.setString(idx++, student.getReligion());
                ps.setString(idx++, student.getPob());
                ps.setString(idx++, student.getNationality());
                ps.setString(idx++, student.getAddress());
                ps.setTimestamp(idx++, Timestamp.valueOf(student.getCreateAt()));
                ps.setTimestamp(idx++, Timestamp.valueOf(student.getCreateAt()));
                if (student.getClassId() == null || student.getClassId().isEmpty()) {
                    ps.setNull(idx++, Types.VARCHAR);
                } else {
                    ps.setString(idx++, student.getClassId());
                }
                ps.setString(idx++, student.getAvatarUrl());
                return ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lưu sinh viên: " + e.getMessage());
            e.printStackTrace();
            return -1;
        } finally {
            if (connection != null) {
                DBUtil.close(connection);
            }
        }
    }

    public Student findByUsername(String username) {
        String sql = "SELECT * FROM students WHERE username = ?";
        try {
            connection = DBUtil.getConnection();
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, username);
                try (var rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Student student = new Student();
                        student.setId(rs.getString("id"));
                        student.setUsername(rs.getString("username"));
                        student.setPrimaryEmail(rs.getString("primary_email"));
                        student.setFirstname(rs.getString("first_name"));
                        student.setLastname(rs.getString("last_name"));
                        student.setHashPassword(rs.getString("hash_password"));
                        return student;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm sinh viên theo tên đăng nhập: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DBUtil.close(connection);
            }
        }
        return null;
    }

    public String getHashedPasswordByUsername(String username) {
        String sql = "SELECT hash_password FROM students WHERE username = ?";
        try {
            connection = DBUtil.getConnection();
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, username);
                try (var rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getString("hash_password");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy mật khẩu theo tên đăng nhập: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DBUtil.close(connection);
            }
        }
        return null;
    }
}
