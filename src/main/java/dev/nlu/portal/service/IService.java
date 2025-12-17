package dev.nlu.portal.service;

import dev.nlu.portal.model.Student;

import java.util.List;

public interface IService<T> {
    public int save(T t);

    public int update(T t);

    int update(Student student);

    public int delete(T t);

    public T find(String id);

    public List<T> findAll();
}
