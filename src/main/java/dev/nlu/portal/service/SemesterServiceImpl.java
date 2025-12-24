package dev.nlu.portal.service;

import dev.nlu.portal.dao.DAO;
import dev.nlu.portal.model.Semester;

import java.util.List;

public class SemesterServiceImpl implements IService<Semester> {
    private final DAO<Semester> dao;

    public SemesterServiceImpl() {
        this.dao = new SemesterDAOImpl();
    }

    @Override
    public void save(Semester semester) {
        dao.save(semester);
    }

    @Override
    public void update(Semester semester) {
        dao.update(semester);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public Semester findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Semester> findAll() {
        return dao.findAll();
    }

    @Override
    public Semester login(Long id, String password) {
        return null; // Not applicable
    }
}
