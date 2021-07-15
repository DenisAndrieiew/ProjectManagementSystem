package com.ProjectManagmentSystem.dao;

import com.ProjectManagmentSystem.dao.model.DevSkillsDAO;
import com.ProjectManagmentSystem.jdbc.config.DatabaseConnectionManager;
import com.ProjectManagmentSystem.service.converter.Converter;
import com.ProjectManagmentSystem.service.converter.DevSkillsConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DevSkillsRepository implements Repository<DevSkillsDAO> {
    public static final String NEXT_DEV_SKILLS_ID = "SELECT MAX(id)+1 FROM dev_skills;";
    private static final String SELECT_DEV_SKILLS_BY_DEV_ID = "SELECT dev_id, skill_id, skill_level_id" +
            "FROM dev_skills WHERE dev_id = ?;";
    private static final String SELECT_DEV_SKILLS_BY_SKILL_ID = "SELECT dev_id, skill_id, skill_level_id" +
            "FROM dev_skills WHERE skill_id = ?;";
    private static final String SELECT_DEV_SKILLS_BY_SKILL_LEVEL_ID = "SELECT dev_id, skill_id, skill_level_id" +
            "FROM dev_skills WHERE skill_level_id = ?;";
    private static final String UPDATE = "UPDATE dev_skills SET dev_id=?, skill_id=?, " +
            "skill_level_id=? WHERE id=?;";
    private static final String INSERT = "INSERT INTO dev_skills (dev_id, skill_id, skill_level_id)" +
            " VALUES (?, ?, ?);";
    private static final String DELETE = "DELETE FROM developers WHERE id=?;";
    private final DatabaseConnectionManager manager;
    private final DevSkillsConverter converter = new DevSkillsConverter();

    public DevSkillsRepository(DatabaseConnectionManager manager) {
        this.manager = manager;
    }


    @Override
    public DevSkillsDAO findById(long id) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEV_SKILLS_BY_DEV_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return converter.fromResultSet(resultSet).get(0);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
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
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Converter getConverter() {
        return this.converter;
    }

    private long getNextId() {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(NEXT_DEV_SKILLS_ID)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.getLong(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
}
