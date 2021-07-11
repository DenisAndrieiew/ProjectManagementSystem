package com.ProjectManagmentSystem.jdbc.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
    private String url;
    private Properties properties;

    public DatabaseConnectionManager(String host, String databaseName, String username, String password) {
        this.url = String.format("jdbc:postgresql://%s%s", host, databaseName);
        this.properties = fillProperties(username, password);
    }
    public Connection getConnection(){
        try {
            return DriverManager.getConnection(url, properties);
        } catch (SQLException throwables) {
            System.err.println(throwables.getMessage());
            throw new RuntimeException(throwables);
        }
    }

    private Properties fillProperties(String username, String password) {
        Properties properties = new Properties();
        properties.setProperty("user", username);
        properties.setProperty("password", password);
        return properties;
    }
}
