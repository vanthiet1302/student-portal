package dev.nlu.portal.service;

import java.sql.Connection;
import java.util.List;

public interface ICrudService<T> {
	T findById(Long id);

	List<T> findAll();

	boolean create(T t);

	boolean update(T t);

	boolean delete(Long id);

	boolean createWithTransaction(T t, Connection conn);

	boolean updateWithTransaction(T t, Connection conn);

	boolean deleteWithTransaction(Long id, Connection conn);
}