package dev.nlu.portal.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dev.nlu.portal.dao.UserDao;
import dev.nlu.portal.exception.BusinessException;
import dev.nlu.portal.model.User;
import dev.nlu.portal.utils.DBUtil;
import dev.nlu.portal.utils.PasswordUtil;

public class UserServiceImpl implements ICrudService<User> {
	private UserDao dao = new UserDao();
	
	public User login(String username, String password) {
		User result = findByUsername(username);
		if (result != null) {
			if (PasswordUtil.checkPassword(password, result.getPasswordHashed()));
		}
		return null;
	}

    public User findByUsername(String username) {
        return dao.findByUsername(username);
    }

	@Override
	public User findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<User> findAll() {
		return dao.findAll();
	}

	@Override
	public boolean create(User user) {
		try (Connection conn = DBUtil.getConnection()) {
			return createWithTransaction(user, conn);
		} catch (SQLException e) {
			throw new BusinessException("", e);
		}
	}

	@Override
	public boolean update(User user) {
		try (Connection conn = DBUtil.getConnection()) {
			return updateWithTransaction(user, conn);
		} catch (SQLException e) {
			throw new BusinessException("", e);
		}
	}

	@Override
	public boolean delete(Long id) {
		try (Connection conn = DBUtil.getConnection()) {
			return deleteWithTransaction(id, conn);
		} catch (SQLException e) {
			throw new BusinessException("", e);
		}
	}

	@Override
	public boolean createWithTransaction(User user, Connection conn) {
		return dao.save(user, conn);
	}

	@Override
	public boolean updateWithTransaction(User user, Connection conn) {
		return dao.update(user, conn);
	}

	@Override
	public boolean deleteWithTransaction(Long userId, Connection conn) {
		return dao.delete(userId, conn);
	}

}
