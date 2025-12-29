package dev.nlu.portal.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dev.nlu.portal.dao.StudentDAO;
import dev.nlu.portal.dao.UserDAO;
import dev.nlu.portal.exception.BusinessException;
import dev.nlu.portal.model.Role;
import dev.nlu.portal.model.Student;
import dev.nlu.portal.utils.DatabaseUtils;

public class StudentService implements ICrudService<Student> {
    private final StudentDAO studentDao = new StudentDAO();
    private final UserDAO userDao = new UserDAO();

    @Override
    public Student findById(String userId) {
        return studentDao.findById(userId);
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public boolean insert(Student student) {
        return executeTransaction((conn)
        		-> insertWithTransaction(student, conn), "Insert Student failed.");
    }

    @Override
    public boolean update(Student student) {
        return executeTransaction((conn) 
        		-> updateWithTransaction(student, conn), "Update Student failed.");
    }

    @Override
    public boolean delete(String userId) {
        return executeTransaction((conn) 
        		-> deleteWithTransaction(userId, conn), "Delete Student failed.");
    }

    @Override
    public boolean insertWithTransaction(Student student, Connection conn){
    	if (student.getUser() == null) return false;
        boolean userSaved = userDao.insert(student.getUser(), conn);
        if (userSaved) {
            student.setUserId(student.getUser().getId());
            return studentDao.insert(student, conn);
        }
        return false;
    }

    @Override
    public boolean updateWithTransaction(Student student, Connection conn){
    	if (student.getUser() == null) return false;
        boolean userUpdated = userDao.update(student.getUser(), conn);
        boolean studentUpdated = studentDao.update(student, conn);
        return userUpdated || studentUpdated;
    }

    @Override
    public boolean deleteWithTransaction(String userId, Connection conn){
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
