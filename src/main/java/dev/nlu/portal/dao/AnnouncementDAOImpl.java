package dev.nlu.portal.dao;

import dev.nlu.portal.model.Announcement;
import dev.nlu.portal.utils.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementDAOImpl implements DAO<Announcement> {
    Connection conn;

    @Override
    public Announcement findById(Long id) {
        String sql = "SELECT * FROM announcements WHERE id = ?";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return mapRowToAnnouncement(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtil.close(conn);
            }
        }
        return null;
    }

    @Override
    public List<Announcement> findAll() {
        String sql = "SELECT * FROM announcements";
        List<Announcement> list = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRowToAnnouncement(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtil.close(conn);
            }
        }
        return list;
    }

    @Override
    public void save(Announcement a) {
        String sql = "INSERT INTO announcements (title, content, posted_by, posted_at, target_id, pinned) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, a.getTitle());
                ps.setString(2, a.getContent());
                ps.setLong(3, a.getPostedBy());
                LocalDateTime postedAt = a.getPostedAt();
                ps.setTimestamp(4, postedAt != null ? Timestamp.valueOf(postedAt) : new Timestamp(System.currentTimeMillis()));
                if (a.getTargetId() == null) {
                    ps.setNull(5, Types.BIGINT);
                } else {
                    ps.setLong(5, a.getTargetId());
                }
                ps.setBoolean(6, a.isPinned());
                ps.executeUpdate();

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        a.setId(generatedKeys.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtil.close(conn);
            }
        }
    }

    @Override
    public void update(Announcement a) {
        String sql = "UPDATE announcements SET title = ?, content = ?, posted_by = ?, posted_at = ?, target_id = ?, pinned = ? WHERE id = ?";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, a.getTitle());
                ps.setString(2, a.getContent());
                ps.setLong(3, a.getPostedBy());
                LocalDateTime postedAt = a.getPostedAt();
                ps.setTimestamp(4, postedAt != null ? Timestamp.valueOf(postedAt) : null);
                if (a.getTargetId() == null) {
                    ps.setNull(5, Types.BIGINT);
                } else {
                    ps.setLong(5, a.getTargetId());
                }
                ps.setBoolean(6, a.isPinned());
                ps.setLong(7, a.getId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtil.close(conn);
            }
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM announcements WHERE id = ?";
        try {
            conn = DBUtil.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtil.close(conn);
            }
        }
    }

    private Announcement mapRowToAnnouncement(ResultSet rs) throws SQLException {
        Announcement a = new Announcement();
        a.setId(rs.getLong("id"));
        a.setTitle(rs.getString("title"));
        a.setContent(rs.getString("content"));
        a.setPostedBy(rs.getLong("posted_by"));
        Timestamp ts = rs.getTimestamp("posted_at");
        a.setPostedAt(ts != null ? ts.toLocalDateTime() : null);
        // a.setScope(...); // scope enum not public; defers mapping
        Long target = rs.getObject("target_id") != null ? rs.getLong("target_id") : null;
        a.setTargetId(target);
        a.setPinned(rs.getBoolean("pinned"));
        return a;
    }
}
