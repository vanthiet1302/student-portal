package dev.nlu.portal.service;

import dev.nlu.portal.dao.CourseDAOImpl;
import dev.nlu.portal.dao.DAO;
import dev.nlu.portal.model.Course;

import java.util.List;

public class CourseServiceImpl implements IService<Course> {
    private final DAO<Course> dao;

    public CourseServiceImpl() {
        this.dao = new CourseDAOImpl();
    }

    @Override
    public void save(Course course) {
        dao.save(course);
    }

    @Override
    public void update(Course course) {
        dao.update(course);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public Course findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Course> findAll() {
        return dao.findAll();
    }

    @Override
    public Course login(Long id, String password) {
        return null; // Not applicable
    }
}
