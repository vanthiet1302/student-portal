package dev.nlu.portal.dao;

import dev.nlu.portal.model.Gender;
import dev.nlu.portal.model.Student;
import dev.nlu.portal.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements DAO<Student> {
    Connection conn;

    @Override
    public Student findById(Long id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return mapRowToStudent(rs);
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
    public List<Student> findAll() {
        String sql = "SELECT * FROM students";
        List<Student> students = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery(sql)) {
                while (rs.next()) {
                    students.add(mapRowToStudent(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtil.close(conn);
            }
        }
        return students;
    }

    @Override
    public void save(Student student) {
        String sql = "INSERT INTO students (user_id, student_code, full_name, email, phone, date_of_birth, gender, address, major, class_name) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
                ps.setLong(1, student.getUserId());
                ps.setString(2, student.getStudentCode());
                ps.setString(3, student.getFullName());
                ps.setString(4, student.getEmail());
                ps.setString(5, student.getPhone());
                ps.setDate(6, Date.valueOf(student.getDateOfBirth()));
                ps.setString(7, student.getGender() != null ? student.getGender().name() : null);
                ps.setString(8, student.getAddress());
                ps.setString(9, student.getMajor());
                ps.setString(10, student.getClassName());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    student.setId(rs.getLong(1));
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
    public void update(Student student) {
        String sql = "UPDATE students SET user_id = ?, student_code = ?, full_name = ?, email = ?, phone = ?, date_of_birth = ?, " +
                "gender = ?, address = ?, major = ?, class_name = ? WHERE id = ?";
        try  {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, student.getUserId());
                ps.setString(2, student.getStudentCode());
                ps.setString(3, student.getFullName());
                ps.setString(4, student.getEmail());
                ps.setString(5, student.getPhone());
                ps.setDate(6, Date.valueOf(student.getDateOfBirth()));
                ps.setString(7, student.getGender() != null ? student.getGender().name() : null);
                ps.setString(8, student.getAddress());
                ps.setString(9, student.getMajor());
                ps.setString(10, student.getClassName());
                ps.setLong(11, student.getId());
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
        String sql = "DELETE FROM students WHERE id = ?";
        try  {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method
    private Student mapRowToStudent(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getLong("id"));
        student.setUserId(rs.getLong("user_id"));
        student.setStudentCode(rs.getString("student_code"));
        student.setFullName(rs.getString("full_name"));
        student.setEmail(rs.getString("email"));
        student.setPhone(rs.getString("phone"));
        student.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
        String genderStr = rs.getString("gender");
        student.setGender(genderStr != null ? Gender.valueOf(genderStr) : null);
        student.setAddress(rs.getString("address"));
        student.setMajor(rs.getString("major"));
        student.setClassName(rs.getString("class_name"));
        return student;
    }

    // Custom finder
    public Student findByEmail(String email) {
        String sql = "SELECT * FROM students WHERE email = ?";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return mapRowToStudent(rs);
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
}