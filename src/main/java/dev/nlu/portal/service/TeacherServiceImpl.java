package dev.nlu.portal.service;

import dev.nlu.portal.dao.DAO;
import dev.nlu.portal.dao.TeacherDAOImpl;
import dev.nlu.portal.model.Teacher;
import dev.nlu.portal.model.User;
import dev.nlu.portal.utils.PasswordUtil;

import java.util.List;

public class TeacherServiceImpl implements IService<Teacher> {
    private final DAO<Teacher> dao;

    public TeacherServiceImpl() {
        this.dao = new TeacherDAOImpl();
    }

    @Override
    public void save(Teacher teacher) {
        dao.save(teacher);
    }

    @Override
    public void update(Teacher teacher) {
        dao.update(teacher);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public Teacher findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Teacher> findAll() {
        return dao.findAll();
    }

    @Override
    public Teacher login(Long id, String password) {
        Teacher teacher = dao.findById(id);
        if (teacher == null) return null;
        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.findById(teacher.getUserId());
        if (user != null && PasswordUtil.checkPassword(password, user.getPasswordHash())) {
            return teacher;
        }
        return null;
    }
}
