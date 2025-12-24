package dev.nlu.portal.dao;

import java.sql.Connection;
import java.util.List;

public interface DAO<T> {
	T findById(Long id);

	List<T> findAll();

	boolean save(T t, Connection conn);

	boolean update(T t, Connection conn);

	boolean delete(Long id, Connection conn);
}