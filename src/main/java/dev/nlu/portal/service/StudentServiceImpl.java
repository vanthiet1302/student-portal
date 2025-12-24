package dev.nlu.portal.service;

import dev.nlu.portal.model.Student;
import dev.nlu.portal.model.User;
import dev.nlu.portal.utils.PasswordUtil;

import java.util.List;

public class StudentServiceImpl implements IService<Student>, IAuthService {
    StudentDAOImpl dao;

    public StudentServiceImpl() {
        dao = new StudentDAOImpl();
    }

    @Override
    public void save(Student student) {
        dao.save(student);
    }

    @Override
    public void update(Student student) {
        dao.update(student);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public Student findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Student> findAll() {
        return dao.findAll();
    }

    @Override
    public Student login(Long id, String password) {
        Student student = dao.findById(id);
        User user = student.getUser();
        if (student != null && user != null) {
            if (PasswordUtil.checkPassword(password, user.getPasswordHash())) {
                return student;
            }
        }
        return null;
    }

    public Student findByEmail(String email) {
        return dao.findByEmail(email);
    }
}
