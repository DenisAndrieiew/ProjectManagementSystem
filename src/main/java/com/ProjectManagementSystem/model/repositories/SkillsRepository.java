package com.ProjectManagementSystem.model.repositories;

import com.ProjectManagementSystem.config.HibernateDatabaseConnector;
import com.ProjectManagementSystem.dto.enums.Brunch;
import com.ProjectManagementSystem.dto.enums.SkillLevel;
import com.ProjectManagementSystem.model.dao.SkillsDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class SkillsRepository implements SkillsRepositoryInterface {
    private static final Logger LOG = LoggerFactory.getLogger(SkillsRepository.class);
    private final SessionFactory sessionFactory;


    public SkillsRepository() {
        this.sessionFactory = HibernateDatabaseConnector.getSessionFactory();
    }

    @Override
    public Set<SkillsDAO> findAll() {
        Set<SkillsDAO> entities = new HashSet<>();
        String queryStatement = "FROM SkillsDAO entity";
        Transaction transaction;
        LOG.debug("open session");
        try (Session session = sessionFactory.openSession()) {
            LOG.debug("session opened");
            LOG.debug("begin transaction");
            transaction = session.beginTransaction();
            Query<SkillsDAO> query = session.createQuery(queryStatement,
                    SkillsDAO.class);
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
    public SkillsDAO findByUniqueName(String param, String value) {
        List<SkillsDAO> entities = new LinkedList();
        String queryString = createQueryByUniqueName(param);
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<SkillsDAO> query = session.createQuery(queryString, SkillsDAO.class);
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
    public SkillsDAO findByBrunchAndLevel(String brunch, String level) {
        List<SkillsDAO> entities = new LinkedList();
        String queryString = "FROM SkillsDAO entity WHERE entity.brunch=: brunch" +
                " AND entity.level=: level";
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<SkillsDAO> query = session.createQuery(queryString, SkillsDAO.class);
            query.setParameter("brunch", Brunch.toBrunch(brunch));
            query.setParameter("level", SkillLevel.toSkillLevel(level));
            LOG.debug("Executing query: " + query.getQueryString());
            entities = query.list();
            transaction.commit();
        } catch (Exception ex) {
            LOG.error(String.format("Cannot find by %s = %s", brunch, level) + ex.getMessage());
            ex.printStackTrace();
        }
        return entities.size() != 0 ? entities.get(0) : null;
    }



    @Override
    public SkillsDAO findById(int id) {
        SkillsDAO dao = null;
        try (Session session = sessionFactory.openSession()) {
            dao = session.get(SkillsDAO.class, id);
        } catch (Exception ex) {
            LOG.error(String.format("findById. Class -%s. Id=%d", "SkillsDAO", id), ex);
        }
        return dao;
    }


    @Override
    public void create(SkillsDAO entity) {
        save(entity);
    }

    @Override
    public void update(SkillsDAO entity) {
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
            LOG.error(String.format("save or update Class -%s. Id=%d", "SkillsDAO", id), ex);
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    private void save(SkillsDAO entity) {
        Transaction transaction = null;
        int id = entity.getId();
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
        } catch (Exception ex) {
            LOG.error(String.format("save or update Class -%s. Id=%d", "SkillsDAO", id), ex);
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }


    private String createQueryByUniqueName(String param) {
        return "FROM SkillsDAO entity WHERE entity." + param + " = :" + param;
    }


}
