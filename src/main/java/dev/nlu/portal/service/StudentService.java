package dev.nlu.portal.service;

import dev.nlu.portal.dao.StudentDAO;
import dev.nlu.portal.model.Student;
import dev.nlu.portal.utils.PasswordUtil;

import java.util.List;

public class StudentService implements IService<Student>, IAuthService {
    StudentDAO studentDAO;
    @Override
    public boolean authenticate(String username, String plainPassword) {
        String storedHashedPassword = studentDAO.getHashedPasswordByUsername(username);

        if (storedHashedPassword == null) {
            return false;
        }

        return PasswordUtil.checkPassword(plainPassword, storedHashedPassword);
    }

    public StudentService(){
        studentDAO=new StudentDAO();
    }

    public boolean exists(String id){
        return studentDAO.find(id)!=null;
    }

    public boolean existsByUsername(String username){
        return studentDAO.findByUsername(username)!=null;
    }


    @Override
    public int save(Student student) {
        return studentDAO.save(student);
    }

    @Override
    public int update(Student student) {
        return studentDAO.update(student);
    }

    @Override
    public int delete(Student student) {
        return studentDAO.delete(student);
    }

    @Override
    public Student find(String id) {
        return studentDAO.find(id);
    }

    @Override
    public List<Student> findAll() {
        return studentDAO.findAll();
    }
}
