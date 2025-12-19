package dev.nlu.portal.service;

import dev.nlu.portal.model.Student;

import java.util.List;

public interface IService<T> {
    public void save(T t);

    public void update(T t);

    public void delete(Long id);

    public T findById(Long id);

    public List<T> findAll();

    T login(Long id, String password);
}
