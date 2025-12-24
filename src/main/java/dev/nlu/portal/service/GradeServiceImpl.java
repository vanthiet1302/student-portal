package dev.nlu.portal.service;

import dev.nlu.portal.model.dto.GradeItemDTO;
import dev.nlu.portal.model.Grade;

import java.util.List;

public class GradeServiceImpl implements IService<Grade> {
    private final GradeDAOImpl dao;

    public GradeServiceImpl() {
        this.dao = new GradeDAOImpl();
    }

    @Override
    public void save(Grade grade) {
        dao.save(grade);
    }

    @Override
    public void update(Grade grade) {
        dao.update(grade);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public Grade findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Grade> findAll() {
        return dao.findAll();
    }

    @Override
    public Grade login(Long id, String password) {
        return null; // Not applicable
    }

    public List<GradeItemDTO> getStudentGradeItems(Long studentId) {
        return dao.findItemsByStudent(studentId);
    }
}
