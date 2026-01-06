package dev.nlu.portal.dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.nlu.portal.exception.DAOException;
import dev.nlu.portal.model.Role;
import dev.nlu.portal.model.User;

public class UserDAO extends BaseDAO implements DAO<User> {

    @Override
    public Optional<User> findById(String id, Connection conn) {
        String sql = """
            SELECT id, username, hashedPassword, email,
                   lastName, firstName, role, enabled,
                   avatarUrl, avatarId, authProvider,
                   createdAt, updatedAt
            FROM Users
            WHERE id = ?
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToUser(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DAOException("Find User by id failed", e);
        }
    }

    public Optional<User> findByUsername(String username, Connection conn) {
        String sql = """
            SELECT id, username, hashedPassword, email,
                   lastName, firstName, role, enabled,
                   avatarUrl, avatarId, authProvider,
                   createdAt, updatedAt
            FROM Users
            WHERE username = ?
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToUser(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DAOException("Find User by username failed", e);
        }
    }

    public Optional<User> findByEmail(String email, Connection conn) {
        String sql = """
            SELECT id, username, hashedPassword, email,
                   lastName, firstName, role, enabled,
                   avatarUrl, avatarId, authProvider,
                   createdAt, updatedAt
            FROM Users
            WHERE email = ?
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToUser(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DAOException("Find User by email failed", e);
        }
    }

    @Override
    public List<User> findAll(Connection conn) {
        List<User> users = new ArrayList<>();

        String sql = """
            SELECT id, username, hashedPassword, email,
                   lastName, firstName, role, enabled,
                   avatarUrl, avatarId, authProvider,
                   createdAt, updatedAt
            FROM Users
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }

            return users;
        } catch (SQLException e) {
            throw new DAOException("Find all Users failed", e);
        }
    }

    @Override
    public void insert(User user, Connection conn) {
        String sql = """
            INSERT INTO Users (
                username, hashedPassword, email,
                lastName, firstName, role,
                enabled, avatarUrl, avatarId, authProvider
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try {
            String id = executeInsert(
                conn, sql,
                user.getUsername(),
                user.getHashedPassword(),
                user.getEmail(),
                user.getLastName(),
                user.getFirstName(),
                user.getRole().name(),
                user.isEnabled(),
                user.getAvatarUrl(),
                user.getAvatarId(),
                user.getAuthProvider()
            );

            user.setId(id);

        } catch (SQLException e) {
            throw new DAOException("Insert User failed", e);
        }
    }

    @Override
    public void update(User user, Connection conn) {
        String sql = """
            UPDATE Users
            SET username = ?,
                hashedPassword = ?,
                email = ?,
                lastName = ?,
                firstName = ?,
                role = ?,
                enabled = ?,
                avatarUrl = ?,
                avatarId = ?,
                authProvider = ?
            WHERE id = ?
        """;

        try {
            int affected = executeUpdate(
                conn, sql,
                user.getUsername(),
                user.getHashedPassword(),
                user.getEmail(),
                user.getLastName(),
                user.getFirstName(),
                user.getRole().name(),
                user.isEnabled(),
                user.getAvatarUrl(),
                user.getAvatarId(),
                user.getAuthProvider(),
                user.getId()
            );

            if (affected == 0) {
                throw new DAOException("Update User failed: user not found");
            }

        } catch (SQLException e) {
            throw new DAOException("Update User failed", e);
        }
    }
    
    @Override
    public void delete(String id, Connection conn) {
        String sql = "DELETE FROM Users WHERE id = ?";

        try {
            int affected = executeUpdate(conn, sql, id);

            if (affected == 0) {
                throw new DAOException("Delete User failed: user not found");
            }

        } catch (SQLException e) {
            throw new DAOException("Delete User failed", e);
        }
    }

	private User mapResultSetToUser(ResultSet rs) throws SQLException {
		var createdAt = rs.getTimestamp("createdAt").toLocalDateTime();
		var updatedAt = rs.getTimestamp("updatedAt").toLocalDateTime();

        return User.builder()
            .id(rs.getString("id"))
            .username(rs.getString("username"))
            .hashedPassword(rs.getString("hashedPassword"))
            .email(rs.getString("email"))
            .lastName(rs.getString("lastName"))
            .firstName(rs.getString("firstName"))
            .role(Role.valueOf(rs.getString("role").toUpperCase()))
            .enabled(rs.getBoolean("enabled"))
            .avatarUrl(rs.getString("avatarUrl"))
            .avatarId(rs.getString("avatarId"))
            .authProvider(rs.getString("authProvider"))
            .createdAt(createdAt)
            .updatedAt(updatedAt)
            .build();
    }
}
