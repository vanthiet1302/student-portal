package dev.nlu.portal.dao;

import java.util.List;

public interface DAO<T> {

    public int save(T t);

    public int update(T t);

    public int delete(T t);

    public T find(String username);

    public List<T> findAll();
    
}
