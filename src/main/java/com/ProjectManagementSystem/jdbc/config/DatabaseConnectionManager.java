package com.ProjectManagementSystem.jdbc.config;

import com.ProjectManagementSystem.service.PropertiesConfig;
import com.ProjectManagementSystem.view.Console;
import com.ProjectManagementSystem.view.View;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionManager {
    private HikariDataSource ds;
private static DatabaseConnectionManager connectionManager;

    public DatabaseConnectionManager(PropertiesConfig propertiesLoader) {
        initDataSource(propertiesLoader);
        connectionManager = this;
    }

    public Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            throw new RuntimeException(throwable);
        }
    }

    private void initDataSource(PropertiesConfig propertiesLoader) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(String.format("jdbc:postgresql://%s/%s", propertiesLoader.getProperty("host"),
                propertiesLoader.getProperty("database.name")));
        config.setUsername(propertiesLoader.getProperty("username"));
        config.setPassword(propertiesLoader.getProperty("password"));
        config.setMaximumPoolSize(10);
        config.setIdleTimeout(10_000);
        config.setConnectionTimeout(10_000);
        ds = new HikariDataSource(config);
    }
public static DatabaseConnectionManager getInstance(){
   return connectionManager;
    }
}

