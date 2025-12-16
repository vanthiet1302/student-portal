package dev.nlu.portal.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {
    private static final String JNDI_NAME = "java:comp/env/jdbc/mysql";
    private static DataSource dataSource;

    static {
        try {
             dataSource = (DataSource) new InitialContext().lookup(JNDI_NAME);

        } catch (NamingException e) {
            System.err.println("Lỗi tra cứu JNDI DataSource: " + e.getMessage());
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Lỗi đóng Connection: " + e.getMessage());
            }
        }
    }
}