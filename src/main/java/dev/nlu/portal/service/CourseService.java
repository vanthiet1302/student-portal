package dev.nlu.portal.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dev.nlu.portal.dao.CourseDAO;
import dev.nlu.portal.exception.BusinessException;
import dev.nlu.portal.model.Course;
import dev.nlu.portal.utils.DatabaseUtils;

public class CourseService implements ICrudService<Course> {
    private final CourseDAO courseDao = new CourseDAO();

    @Override
    public Course findById(String id) {
        return courseDao.findById(id);
    }

    @Override
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    public boolean insert(Course course) {
        return executeTransaction((conn) 
        		-> insertWithTransaction(course, conn), "Insert Course failed.");
    }

    @Override
    public boolean update(Course course) {
        return executeTransaction((conn) 
        		-> updateWithTransaction(course, conn), "Update Course failed.");
    }

    @Override
    public boolean delete(String id) {
        return executeTransaction((conn) 
        		-> deleteWithTransaction(id, conn), "Delete Course failed.");
    }

    @Override
    public boolean insertWithTransaction(Course course, Connection conn) {
        return courseDao.insert(course, conn);
    }

    @Override
    public boolean updateWithTransaction(Course course, Connection conn) {
        return courseDao.update(course, conn);
    }

    @Override
    public boolean deleteWithTransaction(String id, Connection conn) {
        return courseDao.delete(id, conn);
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
                if (e instanceof BusinessException) throw (BusinessException) e;
                throw new BusinessException(errorMessage + ": " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new BusinessException("Error DAOs: " + e.getMessage(), e);
        }
    }
}