package dev.nlu.portal.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dev.nlu.portal.dao.UserDAO;
import dev.nlu.portal.exception.BusinessException;
import dev.nlu.portal.model.User;
import dev.nlu.portal.utils.DatabaseUtils;
import dev.nlu.portal.utils.PasswordUtils;

public class UserService implements ICrudService<User> {
	private final UserDAO dao = new UserDAO();

	public User login(String username, String password) {
		User user = findByUsername(username);
		if (user != null && user.isEnabled() && PasswordUtils.checkpw(password, user.getHashedPassword())) {
			return user;
		}
		return null;
	}

	public User findByUsername(String username) {
		return dao.findByUsername(username);
	}

	public User findByEmail(String email) {
		return dao.findByEmail(email);
	}

	@Override
	public User findById(String id) {
		return dao.findById(id);
	}

	@Override
	public List<User> findAll() {
		return dao.findAll();
	}

	@Override
	public boolean insert(User user) {
		return executeTransaction((conn) -> 
		insertWithTransaction(user, conn), "Insert User failed.");
	}

	@Override
	public boolean update(User user) {
		return executeTransaction((conn) -> 
		updateWithTransaction(user, conn), "Update User failed.");
	}

	@Override
	public boolean delete(String id) {
		return executeTransaction((conn) -> 
		deleteWithTransaction(id, conn), "Delete User failed.");
	}

	@Override
	public boolean insertWithTransaction(User user, Connection conn) {
		return dao.insert(user, conn);
	}

	@Override
	public boolean updateWithTransaction(User user, Connection conn) {
		return dao.update(user, conn);
	}

	@Override
	public boolean deleteWithTransaction(String userId, Connection conn) {
		return dao.delete(userId, conn);
	}

	private boolean executeTransaction(TransactionAction action, String errorMessage) {
		try (Connection conn = DatabaseUtils.getConnection()) {
			conn.setAutoCommit(false);
			try {
				boolean result = action.execute(conn);
				if (result) {
					conn.commit();
					return true;
				} else {
					conn.rollback();
					return false;
				}
			} catch (Exception e) {
				conn.rollback();
				throw new BusinessException(errorMessage, e);
			}
		} catch (SQLException e) {
			throw new BusinessException("Error DAOs: " + e.getMessage(), e);
		}
	}
}