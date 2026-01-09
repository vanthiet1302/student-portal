package dev.nlu.portal.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dev.nlu.portal.dao.FacultyDAO;
import dev.nlu.portal.exception.ServiceException;
import dev.nlu.portal.model.Faculty;
import dev.nlu.portal.utils.DatabaseUtils;

public class FacultyService implements ICrudService<Faculty, String> {

    private final FacultyDAO facultyDao = new FacultyDAO();

    public boolean existsByCode(String code) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            return facultyDao.findByCode(code, conn).isPresent();
        } catch (SQLException e) {
            throw new ServiceException("Check faculty code existence failed", e);
        }
    }

    @Override
    public Faculty getById(String id) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            return facultyDao.findById(id, conn)
                    .orElseThrow(() -> new ServiceException("Faculty not found with id: " + id));
        } catch (SQLException e) {
            throw new ServiceException("Get faculty by id failed", e);
        }
    }

    public Faculty getByCode(String code) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            return facultyDao.findByCode(code, conn)
                    .orElseThrow(() -> new ServiceException("Faculty not found with code: " + code));
        } catch (SQLException e) {
            throw new ServiceException("Get faculty by code failed", e);
        }
    }

    @Override
    public List<Faculty> getAll() {
        try (Connection conn = DatabaseUtils.getConnection()) {
            return facultyDao.findAll(conn);
        } catch (SQLException e) {
            throw new ServiceException("Get all faculties failed", e);
        }
    }

    @Override
    public Faculty create(Faculty faculty) {
        return executeTransaction(conn -> {
            // Kiểm tra trùng mã khoa
            if (facultyDao.findByCode(faculty.getCode(), conn).isPresent()) {
                throw new ServiceException("Faculty code '" + faculty.getCode() + "' already exists");
            }

            facultyDao.insert(faculty, conn);
            return faculty;
        }, "Create faculty failed");
    }

    @Override
    public void update(Faculty faculty) {
        executeTransaction(conn -> {
            // Kiểm tra tồn tại trước khi cập nhật
            facultyDao.findById(faculty.getId(), conn)
                    .orElseThrow(() -> new ServiceException("Cannot update: Faculty not found"));

            // Nếu thay đổi code, cần kiểm tra xem code mới có bị trùng với khoa khác không
            var existing = facultyDao.findByCode(faculty.getCode(), conn);
            if (existing.isPresent() && !existing.get().getId().equals(faculty.getId())) {
                throw new ServiceException("New faculty code already exists in another faculty");
            }

            facultyDao.update(faculty, conn);
            return null;
        }, "Update faculty failed");
    }

    @Override
    public void delete(String id) {
        executeTransaction(conn -> {
            facultyDao.delete(id, conn);
            return null;
        }, "Delete faculty failed");
    }

    /**
     * Reusable Transaction execution logic
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

    @FunctionalInterface
    private interface TransactionCallback<T> {
        T execute(Connection conn) throws Exception;
    }
}