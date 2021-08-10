package com.ProjectManagementSystem.jdbc.config;

import com.ProjectManagementSystem.service.PropertiesConfig;
import com.ProjectManagementSystem.view.Console;
import com.ProjectManagementSystem.view.View;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class DatabaseConnectionManager {
    private static HikariDataSource ds;


    public static void init() {
        PropertiesConfig propertiesLoader = new PropertiesConfig();
        propertiesLoader.loadPropertiesFile("application.properties");
        initDataSource(propertiesLoader);
    }

    public static synchronized HikariDataSource getDataSource() {
        if (Objects.isNull(ds)) {
            init();
            return ds;
        }
        return ds;
    }
    public Connection getConnection() {
        init();
        try {
            return ds.getConnection();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            throw new RuntimeException(throwable);
        }
    }

    private static void initDataSource(PropertiesConfig propertiesLoader) {
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(String.format("jdbc:postgresql://%s/%s", propertiesLoader.getProperty("host"),
                    propertiesLoader.getProperty("database.name")));
            config.setUsername(propertiesLoader.getProperty("username"));
            config.setPassword(propertiesLoader.getProperty("password"));
            config.setMaximumPoolSize(10);
            config.setIdleTimeout(10_000);
            config.setConnectionTimeout(10_000);
            config.setDriverClassName(propertiesLoader.getProperty("jdbc.driver"));
            ds = new HikariDataSource(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


