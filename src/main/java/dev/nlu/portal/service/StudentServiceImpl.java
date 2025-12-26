package dev.nlu.portal.service;

import java.sql.Connection;
import java.util.List;

public class StudentServiceImpl implements ICrudService{

    @Override
    public Object findById(Long id) {
        return null;
    }

    @Override
    public List findAll() {
        return List.of();
    }

    @Override
    public boolean create(Object o) {
        return false;
    }

    @Override
    public boolean update(Object o) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean createWithTransaction(Object o, Connection conn) {
        return false;
    }

    @Override
    public boolean updateWithTransaction(Object o, Connection conn) {
        return false;
    }

    @Override
    public boolean deleteWithTransaction(Long id, Connection conn) {
        return false;
    }
}
