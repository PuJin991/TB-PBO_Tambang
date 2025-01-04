package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/tambang_db"; // Nama database
    private static final String USER = "root"; // Username MySQL
    private static final String PASSWORD = "RHyme12@"; // Password MySQL

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load driver JDBC
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC tidak ditemukan.");
            throw new SQLException("Driver tidak ditemukan.", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
