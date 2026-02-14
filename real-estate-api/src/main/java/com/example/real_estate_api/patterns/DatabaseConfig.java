package com.example.real_estate_api.patterns;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static DatabaseConfig instance;
    private Connection connection;

    private DatabaseConfig() throws SQLException {

        String url = "jdbc:postgresql://localhost:5432/endterm_oop";
        String user = "postgres";
        String pass = "Nurasyl20";
        this.connection = DriverManager.getConnection(url, user, pass);
    }

    public static DatabaseConfig getInstance() throws SQLException {
        if (instance == null || instance.connection.isClosed()) {
            instance = new DatabaseConfig();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}