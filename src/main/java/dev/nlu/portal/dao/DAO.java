package dev.nlu.portal.dao;

import java.util.List;
import java.util.List;

public interface DAO<T> {
    T findById(Long id);               // Tìm theo ID
    List<T> findAll();                  // Lấy tất cả
    void save(T entity);                // Thêm mới
    void update(T entity);              // Cập nhật
    void delete(Long id);               // Xóa theo ID
}