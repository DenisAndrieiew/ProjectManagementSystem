package com.ProjectManagementSystem.model.repositories;

import com.ProjectManagementSystem.config.config.HibernateDatabaseConnector;
import com.ProjectManagementSystem.model.dao.DeveloperDAO;
import com.ProjectManagementSystem.service.converter.Converter;
import com.ProjectManagementSystem.service.converter.DeveloperConverter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class DeveloperRepository implements EntityRepository<DeveloperDAO> {
    private static final Logger LOG = LoggerFactory.getLogger(DeveloperRepository.class);
    private final SessionFactory sessionFactory;


    public DeveloperRepository() {
        this.sessionFactory = HibernateDatabaseConnector.getSessionFactory();
    }

    @Override
    public Set<DeveloperDAO> findAll() {
        Set<DeveloperDAO> entities = new HashSet<>();
        String queryStatement = "FROM DeveloperDAO entity LEFT JOIN FETCH entity.projects LEFT JOIN FETCH entity.devSkills";
        Transaction transaction;
        LOG.debug("open session");
        try (Session session = sessionFactory.openSession()) {
            LOG.debug("session opened");
            LOG.debug("begin transaction");
            transaction = session.beginTransaction();
            Query<DeveloperDAO> query = session.createQuery(queryStatement,
                    DeveloperDAO.class);
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
    public DeveloperDAO findByUniqueName(String param, String value) {
        List<DeveloperDAO> entities = new LinkedList();
        String queryString = createQueryByUniqueName(param);
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<DeveloperDAO> query = session.createQuery(queryString, DeveloperDAO.class);
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

    public DeveloperDAO findEnum(String param, Object value) {
        return null;
    }

    @Override
    public DeveloperDAO findById(int id) {
        DeveloperDAO dao = null;
        try (Session session = sessionFactory.openSession()) {
            dao = session.get(DeveloperDAO.class, id);
        } catch (Exception ex) {
            LOG.error(String.format("findById. Class -%s. Id=%d", DeveloperDAO.class.toString(), id), ex);
        }
        return dao;
    }


    @Override
    public void create(DeveloperDAO entity) {
        save(entity);
    }

    @Override
    public void update(DeveloperDAO entity) {
        save(entity);
    }

    @Override
    public void delete(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(findById(id));
            transaction.commit();
        } catch (Exception ex) {
            LOG.error(String.format("save or update Class -%s. Id=%d", DeveloperDAO.class.toString(), id), ex);
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    private void save(DeveloperDAO entity) {
        Transaction transaction = null;
        int id = entity.getId();
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
        } catch (Exception ex) {
            LOG.error(String.format("save or update Class -%s. Id=%d", DeveloperDAO.class.toString(), id), ex);
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Converter getConverter() {
        return new DeveloperConverter();
    }

    private String createQueryByUniqueName(String param) {
        return "FROM DeveloperDAO " + " entity " + " LEFT JOIN FETCH entity.projects LEFT JOIN FETCH entity.devSkills"
                + " WHERE entity." + param + " = :" + param;
    }


}
