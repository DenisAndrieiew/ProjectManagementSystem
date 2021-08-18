package com.ProjectManagementSystem.model.repositories;

import com.ProjectManagementSystem.config.config.HibernateDatabaseConnector;
import com.ProjectManagementSystem.model.dao.DataAccessObject;
import com.ProjectManagementSystem.model.dao.DeveloperDAO;
import com.ProjectManagementSystem.service.converter.Converter;
import com.ProjectManagementSystem.service.converter.DeveloperConverter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Set;

public class GenericEntityRepository<T extends DataAccessObject> implements EntityRepository<T> {
    private static final Logger LOG = LoggerFactory.getLogger(HibernateDatabaseConnector.class);
    private final SessionFactory sessionFactory;
    private Class<T> entityClass;

    public GenericEntityRepository(Class<T> entityClass) {
        this.sessionFactory = HibernateDatabaseConnector.getSessionFactory();
        this.entityClass = entityClass;
    }

    @Override
    public Set findAll() {
        return null;
    }

    @Override
    public T findById(long id) {
        T dao = null;
        try (Session session = sessionFactory.openSession()) {
            dao = session.get(entityClass, id);
        } catch (Exception ex) {
            LOG.error(String.format("findById. Class -%s. Id=%d", entityClass.toString(), id), ex);
        }
        return dao;
    }


    @Override
    public void create(T entity) {
        save(entity);
    }

    @Override
    public void update(T entity) {
        save(entity);
    }

    @Override
    public void delete(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(findById(id));
            transaction.commit();
        } catch (Exception ex) {
            LOG.error(String.format("save or update Class -%s. Id=%d", entityClass.toString(), id), ex);
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    private void save(T entity) {
        Transaction transaction = null;
        long id = entity.getId();
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
        } catch (Exception ex) {
            LOG.error(String.format("save or update Class -%s. Id=%d", entityClass.toString(), id), ex);
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Converter getConverter() {
        if (entityClass.equals(DeveloperDAO.class)) {
            return new DeveloperConverter();
        } /*else if (entityClass.equals(ProjectsDAO.class)) {
            return new ProjectsConverter();
        } */ else {
            LOG.error("wrong class to convert");
            return null;
        }
    }
}
