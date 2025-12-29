package dev.nlu.portal.dao;

import java.sql.Connection;
import java.util.List;

public interface DAO<T> {
	T findById(String id);

    List<T> findAll();

    boolean insert(T t, Connection conn);

	boolean update(T t, Connection conn);

	boolean delete(String id, Connection conn);
}