package dev.nlu.portal.service;

import java.sql.Connection;
import java.util.List;

public interface ICrudService<T> {
	T findById(String id);

	List<T> findAll();

	boolean insert(T t);

	boolean update(T t);

	boolean delete(String id);

	boolean insertWithTransaction(T t, Connection conn);

	boolean updateWithTransaction(T t, Connection conn);

	boolean deleteWithTransaction(String id, Connection conn);
}