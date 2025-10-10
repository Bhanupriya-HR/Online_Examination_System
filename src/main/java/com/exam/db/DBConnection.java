package com.exam.db;
import java.sql.*;
public class DBConnection {
    private static Connection connection;
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_examination_system", "root", "Bhanu@123");
        }
        return connection;
    }
}
