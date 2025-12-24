package dev.nlu.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.nlu.portal.model.Role;
import dev.nlu.portal.model.Student;
import dev.nlu.portal.model.User;
import dev.nlu.portal.utils.DBUtil;

public class StudentDao extends BaseDAO implements DAO<Student> {

	@Override
	public Student findById(Long id) {
		String sql = "select * from students where id = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return mapResultSetToStudent(rs);
			}

		} catch (SQLException e) {
			System.err.println("Bug -> findById Student: " + e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT s.*, u.username, u.role, u.enabled FROM students s " +
                     "JOIN users u ON s.user_id = u.id";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToStudent(rs));
            }
        } catch (SQLException e) {
            System.err.println("Bug -> findAll Student: " + e.getMessage());
        }
        return list;
    }

	@Override
    public boolean save(Student t, Connection conn){
        String sql = "INSERT INTO students (user_id, student_code, full_name, birthday, gender, " +
                     "status, phone_number, identity_card, ethnicity, religion, birth_place, address) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, t.getUser().getId());
            ps.setString(2, t.getStudentCode());
            ps.setString(3, t.getFullName());
            ps.setDate(4, t.getBirthday() != null ? java.sql.Date.valueOf(t.getBirthday()) : null);
            ps.setString(5, t.getGender());
            ps.setString(6, t.getStatus());
            ps.setString(7, t.getPhoneNumber());
            ps.setString(8, t.getIdentityCard());
            ps.setString(9, t.getEthnicity());
            ps.setString(10, t.getReligion());
            ps.setString(11, t.getBirthPlace());
            ps.setString(12, t.getAddress());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
			System.err.println("Bug -> save Student: " + e.getMessage());
			e.printStackTrace();
		}
        return false;
    }

	@Override
    public boolean update(Student t, Connection conn) {
        String sql = "UPDATE students SET full_name = ?, birthday = ?, gender = ?, status = ?, " +
                     "phone_number = ?, identity_card = ?, address = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, t.getFullName());
            ps.setDate(2, t.getBirthday() != null ? java.sql.Date.valueOf(t.getBirthday()) : null);
            ps.setString(3, t.getGender());
            ps.setString(4, t.getStatus());
            ps.setString(5, t.getPhoneNumber());
            ps.setString(6, t.getIdentityCard());
            ps.setString(7, t.getAddress());
            ps.setLong(8, t.getId());
            
            return ps.executeUpdate() > 0;
        }catch (SQLException e) {
        	System.err.println("Bug -> delete Student: " + e.getMessage());
        	e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Long id, Connection conn){
        String sql = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
        	System.err.println("Bug -> delete Student: " + e.getMessage());
        	e.printStackTrace();
        }
        return false;
    }

	public User mapResultSetToUser(ResultSet rs) throws SQLException {
		return User.builder().id(rs.getLong("user_id")).username(rs.getString("username"))
				.enabled(rs.getBoolean("enabled")).role(Role.valueOf(rs.getString("role"))).build();
	}

	private Student mapResultSetToStudent(ResultSet rs) throws SQLException {
		User user = mapResultSetToUser(rs);

		return Student.builder().id(rs.getLong("id")).user(user).studentCode(rs.getString("student_code"))
				.fullName(rs.getString("full_name"))
				.birthday(rs.getDate("birthday") != null ? rs.getDate("birthday").toLocalDate() : null)
				.gender(rs.getString("gender")).status(rs.getString("status")).phoneNumber(rs.getString("phone_number"))
				.identityCard(rs.getString("identity_card")).ethnicity(rs.getString("ethnicity"))
				.religion(rs.getString("religion")).birthPlace(rs.getString("birth_place"))
				.nationality(rs.getString("nationality")).emailPersonal(rs.getString("email_personal"))
				.address(rs.getString("address")).build();
	}

}
