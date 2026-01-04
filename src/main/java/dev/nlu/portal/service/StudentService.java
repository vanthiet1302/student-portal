package dev.nlu.portal.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dev.nlu.portal.dao.StudentDAO;
import dev.nlu.portal.dao.UserDAO;
import dev.nlu.portal.exception.BusinessException;
import dev.nlu.portal.model.Role;
import dev.nlu.portal.model.Student;
import dev.nlu.portal.model.User;
import dev.nlu.portal.utils.DatabaseUtils;

public class StudentService implements ICrudService<Student, String> {

	private final StudentDAO studentDao = new StudentDAO();
	private final UserDAO userDao = new UserDAO();

	@Override
	public Student getById(String userId) {
		try (Connection conn = DatabaseUtils.getConnection()) {

			Student student = studentDao.findById(userId, conn)
					.orElseThrow(() -> new BusinessException("Student not found"));

			User user = userDao.findById(userId, conn).orElseThrow(
					() -> new BusinessException("User not found"));

			student.setUser(user);
			return student;

		} catch (SQLException e) {
			throw new BusinessException("Get student failed", e);
		}
	}

	@Override
	public List<Student> getAll() {
		try (Connection conn = DatabaseUtils.getConnection()) {
			return studentDao.findAll(conn);
		} catch (SQLException e) {
			throw new BusinessException("Get all students failed", e);
		}
	}

	@Override
	public Student create(Student student) {
		if (student.getUser() == null) {
			throw new BusinessException("Student must have User");
		}

		return executeTransaction(conn -> {

			User user = student.getUser();
			user.setRole(Role.STUDENT);

			userDao.insert(user, conn);

			student.setUserId(user.getId());
			studentDao.insert(student, conn);

			return student;

		}, "Create Student failed");
	}

	@Override
	public void update(Student student) {
		if (student.getUser() == null) {
			throw new BusinessException("Student must have User");
		}

		executeTransaction(conn -> {

			userDao.update(student.getUser(), conn);
			studentDao.update(student, conn);

			return null;

		}, "Update Student failed");
	}

	@Override
	public void delete(String userId) {
		executeTransaction(conn -> {
			userDao.delete(userId, conn);
			return null;

		}, "Delete Student failed");
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
				throw new BusinessException(errorMessage, e);
			}
		} catch (SQLException e) {
			throw new BusinessException("Database error", e);
		}
	}
}
