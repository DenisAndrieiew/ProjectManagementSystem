package com.ProjectManagementSystem.model.repositories;

import com.ProjectManagementSystem.config.config.HibernateDatabaseConnector;
import com.ProjectManagementSystem.model.dao.*;
import com.ProjectManagementSystem.service.converter.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class GenericEntityRepository<T extends DataAccessObject> implements EntityRepository<T> {
    private static final Logger LOG = LoggerFactory.getLogger(GenericEntityRepository.class);
    private final SessionFactory sessionFactory;
    private Class<T> entityClass;


    public GenericEntityRepository(Class<T> entityClass) {
        this.sessionFactory = HibernateDatabaseConnector.getSessionFactory();
        this.entityClass = entityClass;
    }

    @Override
    public Set findAll() {
        Set<T> entities = new HashSet<>();
        String queryStatement = "FROM " + entityClass.getName() + " entity" + getJoin();
        Transaction transaction;
        LOG.debug("open session");
        try (Session session = sessionFactory.openSession()) {
            LOG.debug("session opened");
            LOG.debug("begin transaction");
            transaction = session.beginTransaction();
            Query<T> query = session.createQuery(queryStatement,
                    entityClass);
            LOG.debug("Execute query: " + query.getQueryString());
            entities = query.getResultStream().collect(Collectors.toSet());
            transaction.commit();
        } catch (Exception ex) {
            LOG.error("impossible to execute query: \"" + queryStatement + "\"" + ex.getMessage() +
                    "\n cause: " + ex.getCause());
            ex.printStackTrace();
        }
        return entities;

    }

    @Override
    public T findByUniqueName(String param, String value) {
        List<T> entities = new LinkedList();
        String queryString = createQueryByUniqueName(param);
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<T> query = session.createQuery(queryString, entityClass);
            query.setParameter(param, value);
            LOG.debug("Executing query: " + query.getQueryString());
            entities = query.list();
            transaction.commit();
        } catch (Exception ex) {
            LOG.error(String.format("Cannot find by %s = %s", param, value) + ex.getMessage());
            ex.printStackTrace();
        }
        return entities.size() != 0 ? entities.get(0) : null;
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
        } else if (entityClass.equals(ProjectDAO.class)) {
            return new ProjectConverter();
        } else if (entityClass.equals(CompanyDAO.class)) {
            return new CompanyConverter();
        } else if (entityClass.equals(CustomerDAO.class)) {
            return new CustomerConverter();
        } else if (entityClass.equals(DevSkillsDAO.class)) {
            return new DevSkillsConverter();
        } else {
            LOG.error("wrong class to convert");
            return null;
        }
    }

    private String createQueryByUniqueName(String param) {
        return "FROM " + entityClass.getName() + " entity WHERE entity." + param + " = :" + param;
    }

    private String getJoin() {
        if (entityClass.equals(DeveloperDAO.class)) {
            return " JOIN FETCH entity.projects JOIN FETCH entity.devSkills";
        } else if (entityClass.equals(ProjectDAO.class)) {
            return " JOIN FETCH entity.developers";
        } else if (entityClass.equals(CompanyDAO.class)) {
            return " JOIN FETCH entity.projects";
        } else if (entityClass.equals(CustomerDAO.class)) {
            return " JOIN FETCH entity.projects";
        } else if (entityClass.equals(DevSkillsDAO.class)) {
            return "";
        } else {
            LOG.error("wrong class");
            return null;
        }

    }
}
