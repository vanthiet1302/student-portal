package dev.nlu.portal.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dev.nlu.portal.dao.CourseDAO;
import dev.nlu.portal.exception.ServiceException;
import dev.nlu.portal.model.Course;
import dev.nlu.portal.utils.DatabaseUtils;

public class CourseService implements ICrudService<Course, String> {

    private final CourseDAO courseDao = new CourseDAO();

    public boolean existsByCode(String code) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            return courseDao.findByCode(code, conn).isPresent();
        } catch (SQLException e) {
            throw new ServiceException("Check course code existence failed", e);
        }
    }

    @Override
    public Course getById(String id) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            return courseDao.findById(id, conn)
                    .orElseThrow(() -> new ServiceException("Course not found with id: " + id));
        } catch (SQLException e) {
            throw new ServiceException("Get course by id failed", e);
        }
    }

    public Course getByCode(String code) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            return courseDao.findByCode(code, conn)
                    .orElseThrow(() -> new ServiceException("Course not found with code: " + code));
        } catch (SQLException e) {
            throw new ServiceException("Get course by code failed", e);
        }
    }

    @Override
    public List<Course> getAll() {
        try (Connection conn = DatabaseUtils.getConnection()) {
            return courseDao.findAll(conn);
        } catch (SQLException e) {
            throw new ServiceException("Get all courses failed", e);
        }
    }

    @Override
    public Course create(Course course) {
        return executeTransaction(conn -> {
            // Kiểm tra trùng mã môn học trước khi chèn
            if (courseDao.findByCode(course.getCode(), conn).isPresent()) {
                throw new ServiceException("Course code '" + course.getCode() + "' already exists");
            }

            courseDao.insert(course, conn);
            return course;
        }, "Create course failed");
    }

    @Override
    public void update(Course course) {
        executeTransaction(conn -> {
            // Kiểm tra xem khóa học có tồn tại không trước khi update
            courseDao.findById(course.getId(), conn)
                    .orElseThrow(() -> new ServiceException("Cannot update: Course not found"));

            courseDao.update(course, conn);
            return null;
        }, "Update course failed");
    }

    @Override
    public void delete(String id) {
        executeTransaction(conn -> {
            courseDao.delete(id, conn);
            return null;
        }, "Delete course failed");
    }

    /**
     * Helper method để quản lý Transaction tương tự như trong UserService
     */
    private <T> T executeTransaction(TransactionCallback<T> action, String errorMessage) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            conn.setAutoCommit(false);
            try {
                T result = action.execute(conn);
                conn.commit();
                return result;
            } catch (Exception e) {
                conn.rollback();
                if (e instanceof ServiceException) throw (ServiceException) e;
                throw new ServiceException(errorMessage + ": " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new ServiceException("Database connection error: " + e.getMessage(), e);
        }
    }
}