/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.nlu.portal.dao;

import dev.nlu.portal.model.Student;
import dev.nlu.portal.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class StudentDAO implements DAO<Student> {
    private Connection connection;

    @Override
    public int update(Student student) {
        return 0;
    }

    @Override
    public int delete(Student student) {
        return 0;
    }

    @Override
    public Student find(String id) {
        return null;
    }

    @Override
    public List<Student> findAll() {
        return List.of();
    }

    @Override
    public int save(Student student) {
        try {
            connection = DBUtil.getConnection();
            String sql = "INSERT INTO students (id, username, primary_email, first_name, last_name, hash_password) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                int idx = 1;
                ps.setString(idx++, student.getId());
                ps.setString(idx++, student.getUsername());
                ps.setString(idx++, student.getPrimaryEmail());
                ps.setString(idx++, student.getFirstname());
                ps.setString(idx++, student.getLastname());
                ps.setString(idx++, student.getHashPassword());
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
