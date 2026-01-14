package dev.nlu.portal.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dev.nlu.portal.dao.AcademicClassDAO;
import dev.nlu.portal.exception.ServiceException;
import dev.nlu.portal.model.AcademicClass;
import dev.nlu.portal.utils.DatabaseUtils;

public class AcademicClassService implements ICrudService<AcademicClass, String> {

    private final AcademicClassDAO classDao = new AcademicClassDAO();

    @Override
    public AcademicClass getById(String id) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            return classDao.findById(id, conn)
                    .orElseThrow(() -> new ServiceException("Lớp học không tồn tại với ID: " + id));
        } catch (SQLException e) {
            throw new ServiceException("Lỗi khi lấy thông tin lớp học", e);
        }
    }

    @Override
    public List<AcademicClass> getAll() {
        try (Connection conn = DatabaseUtils.getConnection()) {
            return classDao.findAll(conn);
        } catch (SQLException e) {
            throw new ServiceException("Lỗi khi lấy danh sách lớp học", e);
        }
    }

    public List<AcademicClass> getByFaculty(String facultyId) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            return classDao.findByFacultyId(facultyId, conn);
        } catch (SQLException e) {
            throw new ServiceException("Lỗi khi lấy danh sách lớp theo khoa", e);
        }
    }

    @Override
    public AcademicClass create(AcademicClass clazz) {
        return executeTransaction(conn -> {
            // 1. Kiểm tra trùng mã lớp
            String sqlCheckCode = "SELECT COUNT(*) FROM AcademicClass WHERE code = ?";
            try (var ps = conn.prepareStatement(sqlCheckCode)) {
                ps.setString(1, clazz.getCode());
                var rs = ps.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    throw new ServiceException("Mã lớp '" + clazz.getCode() + "' đã tồn tại!");
                }
            }

            // 2. Kiểm tra khoa (facultyId) có tồn tại không
            // (Bạn có thể gọi FacultyDAO ở đây nếu cần)

            classDao.insert(clazz, conn);
            return clazz;
        }, "Lỗi khi tạo lớp học");
    }

    @Override
    public void update(AcademicClass clazz) {
        executeTransaction(conn -> {
            classDao.findById(clazz.getId(), conn)
                    .orElseThrow(() -> new ServiceException("Không thể cập nhật: Lớp không tồn tại"));

            classDao.update(clazz, conn);
            return null;
        }, "Lỗi khi cập nhật lớp học");
    }

    @Override
    public void delete(String id) {
        executeTransaction(conn -> {
            // Ở đây có thể thêm logic kiểm tra xem lớp có sinh viên không trước khi xóa
            classDao.delete(id, conn);
            return null;
        }, "Lỗi khi xóa lớp học");
    }

    private <T> T executeTransaction(TransactionCallback<T> action, String errorMessage) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            conn.setAutoCommit(false);
            try {
                T result = action.execute(conn);
                conn.commit();
                return result;
            } catch (Exception e) {
                conn.rollback();
                if (e instanceof ServiceException) throw (ServiceException) e;
                throw new ServiceException(errorMessage + ": " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new ServiceException("Lỗi kết nối cơ sở dữ liệu: " + e.getMessage(), e);
        }
    }
}