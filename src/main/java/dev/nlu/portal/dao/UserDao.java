package dev.nlu.portal.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dev.nlu.portal.exception.DAOException;
import dev.nlu.portal.model.Role;
import dev.nlu.portal.model.User;
import dev.nlu.portal.utils.DatabaseUtils;

public class UserDAO extends BaseDAO implements DAO<User> {

	@Override
	public User findById(String id) {
		String sql = "SELECT * FROM Users WHERE id = ?";
		try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, id);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					return mapResultSetToUser(rs);
				}
			}
		} catch (SQLException e) {
			throw new DAOException("Find by Id User failed: " + e.getMessage(), e);
		}
		return null;
	}
	
	public User findByUsername(String username) {
		String sql = "SELECT * FROM Users WHERE username = ?";
		try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, username);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					return mapResultSetToUser(rs);
				}
			}
		} catch (SQLException e) {
			throw new DAOException("Find by Username User failed: " + e.getMessage(), e);
		}
		return null;
	}

	public User findByEmail(String email) {
		String sql = "SELECT * FROM Users WHERE primaryEmail = ? OR personEmail = ?";
		try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, email);
			ps.setString(2, email);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					return mapResultSetToUser(rs);
				}
			}
		} catch (SQLException e) {
			throw new DAOException("Find by Email User failed: " + e.getMessage(), e);
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM Users";
		try (Connection conn = DatabaseUtils.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				users.add(mapResultSetToUser(rs));
			}
		} catch (SQLException e) {
			throw new DAOException("Find all User failed: " + e.getMessage(), e);
		}
		return users;
	}

	@Override
	public boolean insert(User user, Connection conn) {
		String sql = "INSERT INTO users (username, hashedPassword, primaryEmail, personEmail, role, enabled, avatarUrl, avatarId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			String id = executeInsert(conn, sql, user.getUsername(), user.getHashedPassword(), 
					user.getPrimaryEmail(), user.getPersonEmail(),
                    user.getRole().name(), user.isEnabled(),
                    user.getAvatarUrl(), user.getAvatarId());
			if (id != null ) {
				user.setId(id);
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw new DAOException("Insert User failed: " + e.getMessage(), e);
		}
	}

	@Override
	public boolean update(User user, Connection conn) {
		String sql = "UPDATE users SET username=?, hashedPassword=?, primaryEmail=?, personEmail=?, role=?, enabled=?, avatarUrl=?, avatarId=? WHERE id=?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getHashedPassword());
			ps.setString(3, user.getPrimaryEmail());
			ps.setString(4, user.getPersonEmail());
			ps.setString(5, user.getRole().name());
			ps.setBoolean(6, user.isEnabled());
			ps.setString(7, user.getAvatarUrl());
			ps.setString(8, user.getAvatarId());
			ps.setString(9, user.getId());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new DAOException("Update User failed: " + e.getMessage(), e);
		}
	}

	@Override
	public boolean delete(String id, Connection conn) {
		String sql = "DELETE FROM users WHERE id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new DAOException("Delete User failed: " + e.getMessage(), e);
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
                .createdAt(rs.getTimestamp("createdAt").toLocalDateTime())
				.updatedAt(rs.getTimestamp("updatedAt").toLocalDateTime())
                .authProvider(rs.getString("authProvider"))
                .build();
	}
}