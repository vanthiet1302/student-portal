package dev.nlu.portal.service;

import dev.nlu.portal.dao.StudentDAO;
import dev.nlu.portal.model.Student;

import java.util.List;
import java.util.Map;

public class StudentService implements Service<Student> {
    StudentDAO studentDAO;

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
    public int update(String id, Map<String, Object> updates) {
        return studentDAO.update(id, updates);
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
