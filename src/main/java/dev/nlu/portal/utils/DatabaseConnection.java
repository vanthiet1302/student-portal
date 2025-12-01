/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.nlu.portal.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static volatile DatabaseConnection instance;
    private Connection connection;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USER = "admin";
    private static final String PASS = "admin";

    private DatabaseConnection() {
        try {
            this.connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Kết nối Database thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Lỗi kết nối Database: \n" + e.getMessage());
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Đã đóng kết nối Database.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
