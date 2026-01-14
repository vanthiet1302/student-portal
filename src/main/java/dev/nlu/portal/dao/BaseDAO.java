package dev.nlu.portal.dao;

import java.sql.*;

public abstract class BaseDAO {

    protected int executeUpdate(Connection conn, String sql, Object... params) throws SQLException {

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            setParameters(ps, params);
            return ps.executeUpdate();
        }
    }

    protected String executeInsert(Connection conn, String sql, Object... params) throws SQLException {

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            setParameters(ps, params);

            int affected = ps.executeUpdate();
            if (affected != 1) {
                throw new SQLException("Insert failed, affected rows: " + affected);
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }

            throw new SQLException("Insert failed, no generated key returned");
        }
    }

    private void setParameters(PreparedStatement ps, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
    }
}