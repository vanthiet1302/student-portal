package dev.nlu.portal.service;

import java.sql.Connection;
import java.util.List;

public interface ICrudService<T, ID> {

    T getById(ID id);

    List<T> getAll();

    T create(T entity);

    void update(T entity);

    void delete(ID id);
}
