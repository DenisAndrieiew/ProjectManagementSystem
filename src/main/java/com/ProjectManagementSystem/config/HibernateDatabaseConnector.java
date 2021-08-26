package com.ProjectManagementSystem.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class HibernateDatabaseConnector {
    private final static Logger LOG = LoggerFactory.getLogger(HibernateDatabaseConnector.class);
    private static SessionFactory sessionFactory;

    public static synchronized void init() {
        try {
            LOG.debug("Hibernate initialization start");
            MetadataSources source = new MetadataSources(new StandardServiceRegistryBuilder().configure().build());
            final Metadata metadata = source.getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
            LOG.debug("Hibernate initialization complete");
        } catch (Exception e) {
            LOG.error("init. ", e);
        }
    }

    public static synchronized void destroy() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
        LOG.debug("Hibernate destroyed");
    }

    public static SessionFactory getSessionFactory() {
        if (Objects.isNull(sessionFactory)) {
            init();
        }
        return sessionFactory;
    }
}