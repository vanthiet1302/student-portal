package dev.nlu.portal.dao;

import java.util.List;

public interface DAO <T>{
    public void save(T t);
    public void update(T t);
    public T find(String id);
    public List<T> findAll();
    public void delete(T t);
}
