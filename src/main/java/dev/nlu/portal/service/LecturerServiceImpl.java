package dev.nlu.portal.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dev.nlu.portal.dao.LecturerDao;
import dev.nlu.portal.dao.UserDao;
import dev.nlu.portal.exception.BusinessException;
import dev.nlu.portal.model.Lecturer;
import dev.nlu.portal.utils.DBUtil;

public class LecturerServiceImpl implements ICrudService<Lecturer> {
	private LecturerDao dao = new LecturerDao();
	private UserDao userDao = new UserDao();

	@Override
	public Lecturer findById(Long lecturerId) {
		return dao.findById(lecturerId);
	}

	@Override
	public List<Lecturer> findAll() {
		return dao.findAll();
	}

	@Override
	public boolean create(Lecturer lecturer) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			boolean success = createWithTransaction(lecturer, conn);

			if (success) {
				conn.commit();
				return true;
			} else {
				conn.rollback();
				return false;
			}
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ignore) {
				}
			}
			throw new BusinessException("Lỗi khi tạo giảng viên", e);
		} finally {
			if (conn != null) {
				DBUtil.close(conn);
			}
		}
	}

	@Override
	public boolean update(Lecturer lecturer) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			boolean success = updateWithTransaction(lecturer, conn);

			if (success) {
				conn.commit();
				return true;
			} else {
				conn.rollback();
				return false;
			}
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ignore) {
				}
			}
			throw new BusinessException("Lỗi khi cập nhật giảng viên", e);
		} finally {
			if (conn != null) {
				DBUtil.close(conn);
			}
		}
	}

	@Override
	public boolean delete(Long lecturerId) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			boolean success = deleteWithTransaction(lecturerId, conn);

			if (success) {
				conn.commit();
				return true;
			}
			conn.rollback();
			return false;
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ignore) {
				}
			}
			throw new BusinessException("Lỗi khi xóa giảng viên", e);
		} finally {
			if (conn != null) {
				DBUtil.close(conn);
			}
		}
	}

	@Override
	public boolean createWithTransaction(Lecturer lecturer, Connection conn) {
		boolean userSaved = userDao.save(lecturer.getUser(), conn);
		if (userSaved) {
			return dao.save(lecturer, conn);
		}
		return false;
	}

	@Override
	public boolean updateWithTransaction(Lecturer lecturer, Connection conn) {
		boolean userUpdated = userDao.update(lecturer.getUser(), conn);
		boolean lecturerUpdated = dao.update(lecturer, conn);
		return userUpdated && lecturerUpdated;
	}

	@Override
	public boolean deleteWithTransaction(Long lecturerId, Connection conn) {
		Lecturer l = dao.findById(lecturerId);
		if (l != null) {
			boolean lecturerDeleted = dao.delete(lecturerId, conn);
			boolean userDeleted = userDao.delete(l.getUser().getId(), conn);
			return lecturerDeleted && userDeleted;
		}
		return false;
	}
}