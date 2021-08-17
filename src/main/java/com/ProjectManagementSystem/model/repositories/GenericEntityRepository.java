package com.ProjectManagementSystem.model.repositories;

import com.ProjectManagementSystem.config.config.HibernateDatabaseConnector;
import com.ProjectManagementSystem.model.dao.DataAccessObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class GenericEntityRepository<T extends DataAccessObject> implements NewEntityRepository<T> {
    private static final Logger LOG = LoggerFactory.getLogger(HibernateDatabaseConnector.class);
    private final SessionFactory sessionFactory;
    private Class<T> entityClass;

    public GenericEntityRepository(SessionFactory sessionFactory, Class<T> entityClass) {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
    }

    @Override
    public Set FindAll() {
        return null;
    }

    @Override
    public Optional<T> findById(long id) {
        T dao = null;
        try (Session session = sessionFactory.openSession()) {
            return Optional.of(session.get(entityClass, id));
        } catch (Exception ex) {
            LOG.error(String.format("findById. Class -%s. Id=%d", entityClass.toString(), id), ex);
        }
        return Optional.empty();
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
            session.delete(findById(id).orElseThrow());
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


}
