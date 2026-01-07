package dev.nlu.portal.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dev.nlu.portal.dao.StudentDAO;
import dev.nlu.portal.dao.UserDAO;
import dev.nlu.portal.exception.ServiceException;
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
					.orElseThrow(() -> new ServiceException("Student not found"));

			User user = userDao.findById(userId, conn).orElseThrow(
					() -> new ServiceException("User not found"));

			student.setUser(user);
			return student;

		} catch (SQLException e) {
			throw new ServiceException("Get student failed", e);
		}
	}

    @Override
    public List<Student> getAll() {
        try (Connection conn = DatabaseUtils.getConnection()) {
            List<Student> students = studentDao.findAll(conn);

            for (Student student : students) {
                User user = userDao.findById(student.getUserId(), conn).orElse(null);
                student.setUser(user);
            }

            return students;
        } catch (SQLException e) {
            throw new ServiceException("Get all students failed", e);
        }
    }

    @Override
    public Student create(Student student) {
        if (student.getUser() == null) {
            throw new ServiceException("Student must have User info");
        }

        return executeTransaction(conn -> {
            if (userDao.findByUsername(student.getUser().getUsername(), conn).isPresent()) {
                throw new ServiceException("Username already exists");
            }

            User user = student.getUser();
            user.setRole(Role.STUDENT);

            userDao.insert(user, conn);
            System.out.println("Thêm User ID: " + user.getId());

            if (user.getId() == null) {
                throw new ServiceException("Failed to generate User ID");
            }

            student.setUserId(user.getId());
            studentDao.insert(student, conn);

            return student;
        }, "Create Student failed");
    }

	@Override
	public void update(Student student) {
		if (student.getUser() == null) {
			throw new ServiceException("Student must have User");
		}

        executeTransaction(conn -> {

            if (studentDao.findById(student.getUserId(), conn).isEmpty()) {
                throw new ServiceException("Student not found");
            }

            if (!student.getUser().getId().equals(student.getUserId())) {
                throw new ServiceException("User ID mismatch");
            }

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
				throw new ServiceException(errorMessage + ": " + e.getMessage(), e);
			}
		} catch (SQLException e) {
			throw new ServiceException("Database error", e);
		}
	}
}
