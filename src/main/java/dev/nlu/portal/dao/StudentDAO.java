package dev.nlu.portal.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.tags.shaded.org.apache.regexp.recompile;

import dev.nlu.portal.exception.DAOException;
import dev.nlu.portal.model.Role;
import dev.nlu.portal.model.Status;
import dev.nlu.portal.model.Student;
import dev.nlu.portal.model.User;
import dev.nlu.portal.utils.DatabaseUtils;

public class StudentDAO extends BaseDAO implements DAO<Student> {

    @Override
    public Student findById(String userId) {
        String sql = "SELECT * FROM Students s JOIN Users u ON s.userId = u.id WHERE id = ?";
        try (Connection conn = DatabaseUtils.getConnection();
        		PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToStudent(rs);
            }
        } catch (SQLException e) {
        	throw new DAOException("Find by Id Student failed: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students s " + "JOIN users u ON s.userId = u.id";
        try (Connection conn = DatabaseUtils.getConnection(); 
        		PreparedStatement ps = conn.prepareStatement(sql);
        		ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToStudent(rs));
            }
        } catch (SQLException e) {
        	throw new DAOException("Find all User failed: " + e.getMessage(), e);
        }
        return list;
    }

	@Override
	public boolean insert(Student student, Connection conn) {
		try {
			String sql = "INSERT INTO Students ("
                    + "userId, firstName, lastName, "
					+ "dob, pob, gender, status, phoneNumber, citizenId, nation, " 
					+ "religion, nationality, address, classId"
					+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			return executeUpdate(conn, sql, student.getUserId(), student.getFirstName(), student.getLastName(),
					student.getDob(), student.getPob(), student.getGender(), student.getStatus(), student.getPhoneNumber()
					, student.getCitizenId(), student.getNation(), student.getReligion(), student.getNationality()
					, student.getAddress(), student.getClassId()) > 0;
		} catch (SQLException e) {
			throw new DAOException("Insert Student failed: " + e.getMessage(), e);
		}
	}

	@Override
	public boolean update(Student s, Connection conn) {
	    String sql = "UPDATE Students SET " +
	            "firstName = ?, " +
	            "lastName = ?, dob = ?, gender = ?, status = ?, phoneNumber = ?, " +
	            "citizenId = ?, nation = ?, religion = ?, pob = ?, " +
	            "nationality = ?, address = ?, classId = ?" +
	            "WHERE userId = ?";
	    try {
	        int rows = executeUpdate(conn, sql,
                    s.getFirstName(), s.getLastName(),
                    s.getDob(), s.getGender(),
                    s.getStatus(), s.getPhoneNumber(),
                    s.getCitizenId(), s.getNation(),
                    s.getReligion(), s.getPob(),
                    s.getNationality(), s.getAddress(),
                    s.getClassId() ,s.getUserId());
	        return rows > 0;
	    } catch (SQLException e) {
	        throw new DAOException("Update Student failed: " + e.getMessage(), e);
	    }
	}

    @Override
    public boolean delete(String userId, Connection conn) {
        String sql = "DELETE FROM Users WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Delete Student failed: " + e.getMessage(), e);
        }
    }

    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getString("id"))
                .username(rs.getString("username"))
                .hashedPassword(rs.getString("hashedPassword"))
                .primaryEmail(rs.getString("primaryEmail"))
                .personEmail(rs.getString("personEmail"))
                .role(Role.valueOf(rs.getString("role")))
                .enabled(rs.getBoolean("enabled"))
                .avatarUrl(rs.getString("avatarUrl"))
                .avatarId(rs.getString("avatarId"))
                .authProvider(rs.getString("authProvider"))
                .createdAt(rs.getTimestamp("createdAt").toLocalDateTime())
                .updatedAt(rs.getTimestamp("updatedAt").toLocalDateTime())
                .build();
    }

    private Student mapResultSetToStudent(ResultSet rs) throws SQLException {
        User user = mapResultSetToUser(rs);

        return Student.builder()
                .userId(rs.getString("userId"))
                .user(user)
                .lastName(rs.getString("lastName"))
                .firstName(rs.getString("firstName"))
                .dob(rs.getDate("dob").toLocalDate())
                .pob(rs.getString("pob"))
                .gender(rs.getString("gender"))
                .status(Status.valueOf(rs.getString("status")))
                .phoneNumber(rs.getString("phoneNumber"))
                .citizenId(rs.getString("citizenId"))
                .nation(rs.getString("nation"))
                .nationality(rs.getString("nationality"))
                .religion(rs.getString("religion"))
                .address(rs.getString("address"))
                .classId(rs.getString("classId"))
                .build();
    }
}
