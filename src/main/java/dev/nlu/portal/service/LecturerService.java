package dev.nlu.portal.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dev.nlu.portal.dao.LecturerDao;
import dev.nlu.portal.dao.UserDAO;
import dev.nlu.portal.exception.BusinessException;
import dev.nlu.portal.model.Lecturer;
import dev.nlu.portal.utils.DatabaseUtils;

public class LecturerService implements ICrudService<Lecturer> {
    private final LecturerDao lecturerDao = new LecturerDao();
    private final UserDAO userDao = new UserDAO();

    @Override
    public Lecturer findById(String userId) {
        return lecturerDao.findById(userId);
    }

    @Override
    public List<Lecturer> findAll() {
        return lecturerDao.findAll();
    }

    @Override
    public boolean insert(Lecturer lecturer) {
        return executeTransaction((conn) 
        		-> insertWithTransaction(lecturer, conn), "Insert Lecturer failed.");
    }

    @Override
    public boolean update(Lecturer lecturer) {
        return executeTransaction((conn) 
        		-> updateWithTransaction(lecturer, conn), "Update Lecturer failed.");
    }

    @Override
    public boolean delete(String userId) {
        return executeTransaction((conn) 
        		-> deleteWithTransaction(userId, conn), "Delete Lecturer failed.");
    }

    @Override
    public boolean insertWithTransaction(Lecturer lecturer, Connection conn) {
    	if (lecturer.getUser() == null) return false;
        boolean userSaved = userDao.insert(lecturer.getUser(), conn);
        if (userSaved) {
            lecturer.setUserId(lecturer.getUser().getId());
            return lecturerDao.insert(lecturer, conn);
        }
        return false;
    }

    @Override
    public boolean updateWithTransaction(Lecturer lecturer, Connection conn) {
    	if (lecturer.getUser() == null) return false;
        boolean userUpdated = userDao.update(lecturer.getUser(), conn);
        boolean lecturerUpdated = lecturerDao.update(lecturer, conn);
        return userUpdated || lecturerUpdated;
    }

    @Override
    public boolean deleteWithTransaction(String userId, Connection conn) {
        return userDao.delete(userId, conn);
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