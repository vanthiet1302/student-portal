package dev.nlu.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.nlu.portal.exception.DAOException;
import dev.nlu.portal.model.Lecturer;
import dev.nlu.portal.model.Role;
import dev.nlu.portal.model.User;
import dev.nlu.portal.utils.DBUtil;

public class LecturerDao extends BaseDAO implements DAO<Lecturer> {

	@Override
	public boolean save(Lecturer lecturer, Connection conn) {
		StringBuilder sql = new StringBuilder("INSERT INTO lecturers (");
		sql.append("user_id, full_name, birth_year, gender, identity_card, ");
		sql.append("academic_rank, degree, specialization, position, department, ");
		sql.append("work_agency, agency_address, phone_fixed, fax, email_work, ");
		sql.append("email_personal, phone_mobile, bank_account_number, bank_name, bank_branch");
		sql.append(") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		Long id = null;
		try {
			id = executeInsert(conn, sql.toString(), lecturer.getUser() != null ? lecturer.getUser().getId() : null,
					lecturer.getFullName(), lecturer.getBirthYear(), lecturer.getGender(), lecturer.getIdentityCard(),
					lecturer.getAcademicRank(), lecturer.getDegree(), lecturer.getSpecialization(),
					lecturer.getPosition(), lecturer.getDepartment(), lecturer.getWorkAgency(),
					lecturer.getAgencyAddress(), lecturer.getPhoneFixed(), lecturer.getFax(), lecturer.getEmailWork(),
					lecturer.getEmailPersonal(), lecturer.getPhoneMobile(), lecturer.getBankAccountNumber(),
					lecturer.getBankName(), lecturer.getBankBranch());

			if (id != null) {
				lecturer.setId(id);
			}
		} catch (SQLException e) {
			throw new DAOException("Failed to save Lecturer", e);
		}

		return id != null;
	}

	@Override
	public boolean update(Lecturer lecturer, Connection conn) {
		StringBuilder sql = new StringBuilder("UPDATE lecturers SET ");
		sql.append("user_id = ?, full_name = ?, birth_year = ?, gender = ?, identity_card = ?, ");
		sql.append("academic_rank = ?, degree = ?, specialization = ?, position = ?, department = ?, ");
		sql.append("work_agency = ?, agency_address = ?, phone_fixed = ?, fax = ?, email_work = ?, ");
		sql.append("email_personal = ?, phone_mobile = ?, bank_account_number = ?, bank_name = ?, bank_branch = ? ");
		sql.append("WHERE id = ?");

		try {
			int affectedRows = executeUpdate(conn, sql.toString(),
					lecturer.getUser() != null ? lecturer.getUser().getId() : null, lecturer.getFullName(),
					lecturer.getBirthYear(), lecturer.getGender(), lecturer.getIdentityCard(),
					lecturer.getAcademicRank(), lecturer.getDegree(), lecturer.getSpecialization(),
					lecturer.getPosition(), lecturer.getDepartment(), lecturer.getWorkAgency(),
					lecturer.getAgencyAddress(), lecturer.getPhoneFixed(), lecturer.getFax(), lecturer.getEmailWork(),
					lecturer.getEmailPersonal(), lecturer.getPhoneMobile(), lecturer.getBankAccountNumber(),
					lecturer.getBankName(), lecturer.getBankBranch(), lecturer.getId());

			return affectedRows > 0;
		} catch (SQLException e) {
			throw new DAOException("Failed to update Lecturer", e);
		}
	}

	@Override
	public boolean delete(Long id, Connection conn) {
		String sql = "DELETE FROM lecturers WHERE id = ?";

		try {
			int affectedRows = executeUpdate(conn, sql, id);
			return affectedRows > 0;
		} catch (SQLException e) {
			throw new DAOException("Failed to delete Lecturer", e);
		}
	}

	@Override
	public Lecturer findById(Long lecturerId) {
		String sql = "SELECT l.*, u.* " + "FROM lecturers l "
				+ "JOIN users u ON l.user_id = u.id " + "WHERE l.id = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setLong(1, lecturerId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapResultSetToLecturer(rs);
				}
			}
		} catch (SQLException e) {
			throw new DAOException("Failed to find Lecturer by id", e);
		}
		return null;
	}

	public Lecturer findByUsername(String username) {
		String sql = "SELECT l.*, u.* " + "FROM lecturers l "
				+ "JOIN users u ON l.user_id = u.id " + "WHERE u.username = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, username);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapResultSetToLecturer(rs);
				}
			}
		} catch (SQLException e) {
			throw new DAOException("Failed to find Lecturer by username", e);
		}
		return null;
	}

	@Override
	public List<Lecturer> findAll() {
		String sql = "SELECT l.*, u.* " + "FROM lecturers l "
				+ "JOIN users u ON l.user_id = u.id";

		List<Lecturer> result = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				result.add(mapResultSetToLecturer(rs));
			}
		} catch (SQLException e) {
			throw new DAOException("Failed to find all Lecturer", e);
		}
		return result;
	}

	private Lecturer mapResultSetToLecturer(ResultSet rs) throws SQLException {
		User user = mapRowToUser(rs);

		return Lecturer.builder().id(rs.getLong("id")).user(user) // Associate (1-1)
				.fullName(rs.getString("full_name")).birthYear(rs.getInt("birth_year")).gender(rs.getString("gender"))
				.identityCard(rs.getString("identity_card")).academicRank(rs.getString("academic_rank"))
				.degree(rs.getString("degree")).specialization(rs.getString("specialization"))
				.position(rs.getString("position")).department(rs.getString("department"))
				.workAgency(rs.getString("work_agency")).agencyAddress(rs.getString("agency_address"))
				.phoneFixed(rs.getString("phone_fixed")).fax(rs.getString("fax")).emailWork(rs.getString("email_work"))
				.emailPersonal(rs.getString("email_personal")).phoneMobile(rs.getString("phone_mobile"))
				.bankAccountNumber(rs.getString("bank_account_number")).bankName(rs.getString("bank_name"))
				.bankBranch(rs.getString("bank_branch")).build();
	}

	private User mapRowToUser(ResultSet rs) throws SQLException {
		String role = rs.getString("role");
		return User.builder().id(rs.getLong("user_id")).username(rs.getString("username"))
                .passwordHashed(rs.getString("password_hash"))
				.role(Role.valueOf(role.toUpperCase())).enabled(rs.getBoolean("enabled")).build();
	}

}
