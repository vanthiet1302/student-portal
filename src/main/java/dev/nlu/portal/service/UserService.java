package dev.nlu.portal.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dev.nlu.portal.dao.UserDAO;
import dev.nlu.portal.exception.ServiceException;
import dev.nlu.portal.model.User;
import dev.nlu.portal.utils.DatabaseUtils;
import dev.nlu.portal.utils.PasswordUtils;

public class UserService implements ICrudService<User, String>{

	private final UserDAO userDao = new UserDAO();

    public boolean existsByUsername(String username) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            return userDao.findByUsername(username, conn).isPresent();
        } catch (SQLException e) {
            throw new ServiceException("Check username existence failed", e);
        }
    }

    public boolean existsByEmail(String email) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            return userDao.findByEmail(email, conn).isPresent();
        } catch (SQLException e) {
            throw new ServiceException("Check email existence failed", e);
        }
    }


    public User login(String username, String password) {
        try (Connection conn = DatabaseUtils.getConnection()) {

            User user = userDao.findByUsername(username, conn)
                    .orElseThrow(() -> new ServiceException("Invalid username or password"));

            if (!PasswordUtils.verify(password, user.getHashedPassword())) {
                throw new ServiceException("Invalid username or password");
            }
            user.setHashedPassword(null);

            return user;

        } catch (SQLException e) {
            throw new ServiceException("Login failed", e);
        }
    }

	public User getById(String id) {
		try (Connection conn = DatabaseUtils.getConnection()) {
			return userDao.findById(id, conn).orElseThrow(
					() -> new ServiceException("User not found"));
		} catch (SQLException e) {
			throw new ServiceException("Get user by id failed", e);
		}
	}

	public User getByUsername(String username) {
		try (Connection conn = DatabaseUtils.getConnection()) {
			return userDao.findByUsername(username, conn).orElseThrow(
					() -> new ServiceException("User not found"));
		} catch (SQLException e) {
			throw new ServiceException("Get user by username failed", e);
		}
	}

	public User getByEmail(String email) {
		try (Connection conn = DatabaseUtils.getConnection()) {
			return userDao.findByEmail(email, conn).orElseThrow(
					() -> new ServiceException("User not found"));
		} catch (SQLException e) {
			throw new ServiceException("Get user by email failed", e);
		}
	}

	public List<User> getAll() {
		try (Connection conn = DatabaseUtils.getConnection()) {
			return userDao.findAll(conn);
		} catch (SQLException e) {
			throw new ServiceException("Get all users failed", e);
		}
	}

    public User create(User user) {
        return executeTransaction(conn -> {

            if (userDao.findByUsername(user.getUsername(), conn).isPresent()) {
                throw new ServiceException("Username already exists");
            }

            if (userDao.findByEmail(user.getEmail(), conn).isPresent()) {
                throw new ServiceException("Email already exists");
            }

            userDao.insert(user, conn);
            return user;

        }, "Create user failed");
    }


    public void update(User user) {
		executeTransaction(conn -> {
			userDao.update(user, conn);
			return git add .;
		}, "Update user failed");
	}

	public void delete(String id) {
		executeTransaction(conn -> {
			userDao.delete(id, conn);
			return null;
		}, "Delete user failed");
	}

	private <T> T executeTransaction(TransactionCallback<T> action, String errorMessage) {
		try (Connection conn = DatabaseUtils.getConnection()) {
			conn.setAutoCommit(false);
			try {
				T result = action.execute(conn);
				conn.commit();
				return result;
			} catch (Exception e) {
				conn.rollback();
				throw new ServiceException(errorMessage + ": " + e.getMessage(), e);
			}
		} catch (SQLException e) {
			throw new ServiceException("Database error: " + e.getMessage(), e);
		}
	}
}
