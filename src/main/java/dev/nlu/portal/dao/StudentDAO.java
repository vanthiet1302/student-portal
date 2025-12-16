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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class StudentDAO implements DAO<Student> {
    private Connection connection;

    public StudentDAO() {
    }


    @Override
    public int save(Student t) {
        String sql = "INSERT INTO Students (name, email) VALUES (?, ?)";

        return 0;
    }

    @Override
    public int update(String id, Map<String, Object> updates) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(Student t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Student find(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Student> findAll() {
        List<Student> result = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            try (PreparedStatement ps = connection.prepareStatement("select * from student_mock");) {
                try (ResultSet rs = ps.executeQuery();) {
                    while (rs.next()) {
                        result.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age")));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi mở Connection: " + e.getMessage());
            e.printStackTrace();
        }
        DBUtil.close(connection);
        return result;
    }

}
