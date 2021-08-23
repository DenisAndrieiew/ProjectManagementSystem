package com.ProjectManagementSystem.model.repositories;

import com.ProjectManagementSystem.config.config.HibernateDatabaseConnector;
import com.ProjectManagementSystem.dto.enums.Brunch;
import com.ProjectManagementSystem.dto.enums.SkillLevel;
import com.ProjectManagementSystem.model.dao.DevSkillsDAO;
import com.ProjectManagementSystem.service.converter.Converter;
import com.ProjectManagementSystem.service.converter.DevSkillsConverter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class DevSkillsRepository implements SkillsRepository {
    private static final Logger LOG = LoggerFactory.getLogger(DevSkillsRepository.class);
    private final SessionFactory sessionFactory;


    public DevSkillsRepository() {
        this.sessionFactory = HibernateDatabaseConnector.getSessionFactory();
    }

    @Override
    public Set<DevSkillsDAO> findAll() {
        Set<DevSkillsDAO> entities = new HashSet<>();
        String queryStatement = "FROM DevSkills entity";
        Transaction transaction;
        LOG.debug("open session");
        try (Session session = sessionFactory.openSession()) {
            LOG.debug("session opened");
            LOG.debug("begin transaction");
            transaction = session.beginTransaction();
            Query<DevSkillsDAO> query = session.createQuery(queryStatement,
                    DevSkillsDAO.class);
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
    public DevSkillsDAO findByUniqueName(String param, String value) {
        List<DevSkillsDAO> entities = new LinkedList();
        String queryString = createQueryByUniqueName(param);
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<DevSkillsDAO> query = session.createQuery(queryString, DevSkillsDAO.class);
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
    public DevSkillsDAO findByBrunchAndLevel(String brunch, String level) {
        List<DevSkillsDAO> entities = new LinkedList();
        String queryString = "FROM DevSkillsDAO entity WHERE entity.brunch=: brunch" +
                " AND entity.level=: level";
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<DevSkillsDAO> query = session.createQuery(queryString, DevSkillsDAO.class);
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
    public DevSkillsDAO findById(int id) {
        DevSkillsDAO dao = null;
        try (Session session = sessionFactory.openSession()) {
            dao = session.get(DevSkillsDAO.class, id);
        } catch (Exception ex) {
            LOG.error(String.format("findById. Class -%s. Id=%d", "DevSkillsDAO", id), ex);
        }
        return dao;
    }


    @Override
    public void create(DevSkillsDAO entity) {
        save(entity);
    }

    @Override
    public void update(DevSkillsDAO entity) {
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
            LOG.error(String.format("save or update Class -%s. Id=%d", "DevSkillsDAO", id), ex);
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    private void save(DevSkillsDAO entity) {
        Transaction transaction = null;
        int id = entity.getId();
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
        } catch (Exception ex) {
            LOG.error(String.format("save or update Class -%s. Id=%d", "DevSkillsDAO", id), ex);
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Converter getConverter() {
        return new DevSkillsConverter();
    }

    private String createQueryByUniqueName(String param) {
        return "FROM DevSkillsDAO entity WHERE entity." + param + " = :" + param;
    }


}
