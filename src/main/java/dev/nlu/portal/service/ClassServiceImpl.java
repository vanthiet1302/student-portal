package dev.nlu.portal.service;

import dev.nlu.portal.dao.ClassDAOImpl;
import dev.nlu.portal.dao.DAO;
import dev.nlu.portal.model.Class;

import java.util.List;

public class ClassServiceImpl implements IService<Class> {
    private final DAO<Class> dao;

    public ClassServiceImpl() {
        this.dao = new ClassDAOImpl();
    }

    @Override
    public void save(Class c) {
        dao.save(c);
    }

    @Override
    public void update(Class c) {
        dao.update(c);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public Class findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Class> findAll() {
        return dao.findAll();
    }

    @Override
    public Class login(Long id, String password) {
        return null; // Not applicable
    }
}
