package dev.nlu.portal.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dev.nlu.portal.dao.LecturerDAO;
import dev.nlu.portal.dao.UserDAO;
import dev.nlu.portal.exception.BusinessException;
import dev.nlu.portal.model.Lecturer;
import dev.nlu.portal.model.Role;
import dev.nlu.portal.model.User;
import dev.nlu.portal.utils.DatabaseUtils;

public class LecturerService implements ICrudService<Lecturer, String> {

    private final LecturerDAO lecturerDao = new LecturerDAO();
    private final UserDAO userDao = new UserDAO();

    /* =========================
       READ
       ========================= */

    @Override
    public Lecturer getById(String userId) {
        try (Connection conn = DatabaseUtils.getConnection()) {

            Lecturer lecturer = lecturerDao.findById(userId, conn)
                .orElseThrow(() ->
                    new BusinessException("Lecturer not found"));

            User user = userDao.findById(userId, conn)
                .orElseThrow(() ->
                    new BusinessException("User not found"));

            lecturer.setUser(user);
            return lecturer;

        } catch (SQLException e) {
            throw new BusinessException("Get lecturer failed", e);
        }
    }

    @Override
    public List<Lecturer> getAll() {
        try (Connection conn = DatabaseUtils.getConnection()) {
            return lecturerDao.findAll(conn);
        } catch (SQLException e) {
            throw new BusinessException("Get all lecturers failed", e);
        }
    }

    public List<Lecturer> getAllPaginated(int page, int pageSize) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            return lecturerDao.findAllPaginated(page, pageSize, conn);
        } catch (SQLException e) {
            throw new BusinessException("Get lecturers paginated failed", e);
        }
    }

    public int countAll() {
        try (Connection conn = DatabaseUtils.getConnection()) {
            return lecturerDao.countAll(conn);
        } catch (SQLException e) {
            throw new BusinessException("Count lecturers failed", e);
        }
    }

    @Override
    public Lecturer create(Lecturer lecturer) {
        if (lecturer.getUser() == null) {
            throw new BusinessException("Lecturer must have User");
        }

        return executeTransaction(conn -> {

            User user = lecturer.getUser();
            user.setRole(Role.LECTURER);

            userDao.insert(user, conn);

            lecturer.setUserId(user.getId());
            lecturerDao.insert(lecturer, conn);

            return lecturer;

        }, "Create Lecturer failed");
    }

    @Override
    public void update(Lecturer lecturer) {
        if (lecturer.getUser() == null) {
            throw new BusinessException("Lecturer must have User");
        }

        executeTransaction(conn -> {

            userDao.update(lecturer.getUser(), conn);
            lecturerDao.update(lecturer, conn);

            return null;

        }, "Update Lecturer failed");
    }

    @Override
    public void delete(String userId) {
        executeTransaction(conn -> {
            userDao.delete(userId, conn);
            return null;

        }, "Delete Lecturer failed");
    }
    private <T> T executeTransaction(
            TransactionCallback<T> action,
            String errorMessage
    ) {
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
