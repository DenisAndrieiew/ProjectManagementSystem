package com.ProjectManagementSystem.model.repositories;

import com.ProjectManagementSystem.config.HibernateDatabaseConnector;
import com.ProjectManagementSystem.model.dao.CompanyDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class CompanyRepository implements EntityRepository<CompanyDAO> {
    private static final Logger LOG = LoggerFactory.getLogger(CompanyRepository.class);
    private final SessionFactory sessionFactory;


    public CompanyRepository() {
        this.sessionFactory = HibernateDatabaseConnector.getSessionFactory();
    }

    @Override
    public Set<CompanyDAO> findAll() {
        Set<CompanyDAO> entities = new HashSet<>();
        String queryStatement = "FROM CompanyDAO entity LEFT JOIN FETCH entity.projects";
        Transaction transaction;
        LOG.debug("open session");
        try (Session session = sessionFactory.openSession()) {
            LOG.debug("session opened");
            LOG.debug("begin transaction");
            transaction = session.beginTransaction();
            Query<CompanyDAO> query = session.createQuery(queryStatement,
                    CompanyDAO.class);
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
    public CompanyDAO findByUniqueName(String param, String value) {
        List<CompanyDAO> entities = new LinkedList();
        String queryString = createQueryByUniqueName(param);
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<CompanyDAO> query = session.createQuery(queryString, CompanyDAO.class);
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
    public CompanyDAO findById(int id) {
        CompanyDAO dao = null;
        try (Session session = sessionFactory.openSession()) {
            dao = session.get(CompanyDAO.class, id);
        } catch (Exception ex) {
            LOG.error(String.format("findById. Id=%d", id), ex);
        }
        return dao;
    }


    @Override
    public void create(CompanyDAO entity) {
        save(entity);
    }

    @Override
    public void update(CompanyDAO entity) {
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
            LOG.error(String.format("save or update. Id=%d", id), ex);
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    private void save(CompanyDAO entity) {
        Transaction transaction = null;
        int id = entity.getId();
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
        } catch (Exception ex) {
            LOG.error(String.format("save or update. Id=%d", id), ex);
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }


    private String createQueryByUniqueName(String param) {
        return "FROM CompanyDAO  entity  WHERE entity." + param + " = :" + param;
    }

}
