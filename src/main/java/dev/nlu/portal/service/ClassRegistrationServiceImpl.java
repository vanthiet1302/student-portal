package dev.nlu.portal.service;

import dev.nlu.portal.dao.ClassRegistrationDAOImpl;
import dev.nlu.portal.dao.DAO;
import dev.nlu.portal.model.ClassRegistration;

import java.util.List;

public class ClassRegistrationServiceImpl implements IService<ClassRegistration> {
    private final DAO<ClassRegistration> dao;

    public ClassRegistrationServiceImpl() {
        this.dao = new ClassRegistrationDAOImpl();
    }

    @Override
    public void save(ClassRegistration classRegistration) {
        dao.save(classRegistration);
    }

    @Override
    public void update(ClassRegistration classRegistration) {
        dao.update(classRegistration);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public ClassRegistration findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<ClassRegistration> findAll() {
        return dao.findAll();
    }

    @Override
    public ClassRegistration login(Long id, String password) {
        return null; // Not applicable
    }
}
