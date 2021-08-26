package com.ProjectManagementSystem.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppInit implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        HibernateDatabaseConnector.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
