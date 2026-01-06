package dev.nlu.portal.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {
	Optional<T> findById(String id, Connection conn);

    List<T> findAll(Connection conn);

    void insert(T t, Connection conn);

	void update(T t, Connection conn);

	void delete(String id, Connection conn);
}