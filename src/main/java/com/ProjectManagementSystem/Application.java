package com.ProjectManagementSystem;

import com.ProjectManagementSystem.controller.MainController;
import com.ProjectManagementSystem.jdbc.config.DatabaseConnectionManager;
import com.ProjectManagementSystem.service.PropertiesConfig;

public class Application {
    public static void main(String[] args) {
        PropertiesConfig propertiesLoader = new PropertiesConfig();
        propertiesLoader.loadPropertiesFile("application.properties");
        DatabaseConnectionManager connectionManager = new DatabaseConnectionManager(propertiesLoader);
        MainController controller = new MainController();
        controller.run();

    }
}
