package dev.nlu.portal.dao;

import dev.nlu.portal.model.Role;
import dev.nlu.portal.model.User;
import dev.nlu.portal.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.tags.shaded.org.apache.regexp.recompile;

public class UserDao extends BaseDAO implements DAO<User> {

	@Override
	public boolean save(User user, Connection conn) {
		String sql = "INSERT INTO users (username, password_hash, role, enabled, created_at, updated_at) "
				+ "VALUES (?, ?, ?, ?, NOW(), NOW())";

		try {
			Long id = executeInsert(conn, sql, user.getUsername(), user.getPasswordHashed(),
					user.getRole() != null ? user.getRole().name() : null, user.isEnabled());

			if (id != null) {
				user.setId(id);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// NOte để chỉnh lại update bằng username
	@Override
	public boolean update(User user, Connection conn) {
		String sql = "UPDATE users SET username = ?, password_hash = ?, role = ?, enabled = ?, updated_at = NOW() WHERE id = ?";
		try {
			int rows = executeUpdate(conn, sql, user.getUsername(), user.getPasswordHashed(),
					user.getRole() != null ? user.getRole().name() : null, user.isEnabled(), user.getId());
			return rows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Long id, Connection conn) {
		String sql = "DELETE FROM users WHERE id = ?";
		try {
			return executeUpdate(conn, sql, id) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User findById(Long id) {
		String sql = "SELECT * FROM users WHERE id = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setLong(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapRowToUser(rs);
				}
			}
		} catch (SQLException e) {
			System.err.println("Bug -> findById User: " + e.getMessage());
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		String sql = "SELECT * FROM users";
		List<User> result = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				result.add(mapRowToUser(rs));
			}
		} catch (SQLException e) {
			System.err.println("Bug -> findAll User: " + e.getMessage());
		}
		return result;
	}

	public User findByUsername(String username) {
		String sql = "select * from users where username = ?";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();) {
			if (rs.next()) {
				return mapRowToUser(rs);
			}
		} catch (SQLException e) {
			System.err.println("Bug -> findByUsername User: " + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	private User mapRowToUser(ResultSet rs) throws SQLException {
		return User.builder().id(rs.getLong("id")).username(rs.getString("username"))
				.role(rs.getString("role") != null ? Role.valueOf(rs.getString("role")) : null)
				.enabled(rs.getBoolean("enabled")).createdAt(rs.getTimestamp("created_at").toLocalDateTime())
				.updatedAt(rs.getTimestamp("updated_at").toLocalDateTime()).build();
	}
}