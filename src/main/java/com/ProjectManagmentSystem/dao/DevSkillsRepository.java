package com.ProjectManagmentSystem.dao;

import com.ProjectManagmentSystem.dao.model.DevSkillsDAO;
import com.ProjectManagmentSystem.dto.DevSkillsDTO;
import com.ProjectManagmentSystem.jdbc.config.DatabaseConnectionManager;
import com.ProjectManagmentSystem.service.converter.Converter;
import com.ProjectManagmentSystem.service.converter.DevSkillsConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class DevSkillsRepository implements Repository<DevSkillsDAO> {
    public static final String NEXT_ID = "SELECT MAX(id)+1 FROM dev_skills;";
    private static final String SELECT_BY_ID = "SELECT id, dev_id, skill_id, skill_level_id" +
            "FROM dev_skills WHERE id = ?;";
    private static final String SELECT_BY = "SELECT id, dev_id, skill_id, skill_level_id" +
            "FROM dev_skills WHERE ;";
    private static final String UPDATE = "UPDATE dev_skills SET dev_id=?, skill_id=?, " +
            "skill_level_id=? WHERE id=?;";
    private static final String INSERT = "INSERT INTO dev_skills (dev_id, skill_id, skill_level_id)" +
            " VALUES (?, ?, ?);";
    private static final String DELETE = "DELETE FROM developers WHERE id=?;";
    private final DatabaseConnectionManager manager;
    private final Converter<DevSkillsDAO, DevSkillsDTO> converter = new DevSkillsConverter();

    public DevSkillsRepository(DatabaseConnectionManager manager) {
        this.manager = manager;
    }


    @Override
    public DevSkillsDAO findById(long id) {
        return (DevSkillsDAO) RepositoryUtils.findById(manager, converter, SELECT_BY_ID, id).get(0);
    }
    @Override
    public List<DevSkillsDAO> findByString(String requestField, String requestText) {
        return RepositoryUtils.findByString(manager, converter, SELECT_BY, requestField, requestText).stream()
                .map(dao->(DevSkillsDAO)dao).collect(Collectors.toList());
    }

    @Override
    public List<DevSkillsDAO> findByNumber(String requestField, long requestNumber) {
        return RepositoryUtils.findByNumber(manager, converter, SELECT_BY, requestField, requestNumber).stream()
                .map(dao->(DevSkillsDAO)dao).collect(Collectors.toList());
    }


    @Override
    public void create(DevSkillsDAO entity) {
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setLong(1, getNextId());
            statement.setLong(2, entity.getDevId());
            statement.setLong(3, entity.getSkillId());
            statement.setLong(4, entity.getSkillLevel());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(DevSkillsDAO entity) {
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setLong(4, entity.getId());
            statement.setLong(1, entity.getDevId());
            statement.setLong(2, entity.getSkillId());
            statement.setLong(3, entity.getSkillLevel());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        RepositoryUtils.delete(manager, DELETE, id);
    }

    @Override
    public Converter getConverter() {
        return this.converter;
    }


    private long getNextId() {
       return RepositoryUtils.getNextId(manager, NEXT_ID);
    }
}
