package com.ProjectManagmentSystem.dao;

import com.ProjectManagmentSystem.dao.model.DevSkillsDAO;
import com.ProjectManagmentSystem.dao.model.DeveloperDAO;
import com.ProjectManagmentSystem.jdbc.config.DatabaseConnectionManager;
import com.ProjectManagmentSystem.service.converter.Converter;
import com.ProjectManagmentSystem.service.converter.DevSkillsConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DevSkillsRepository implements Repository<DevSkillsDAO> {
    private static final String SELECT_DEV_SKILLS_BY_DEV_ID = "SELECT dev_id, skill_id, skill_level_id" +
            "FROM dev_skills WHERE dev_id = ?;";
    private static final String SELECT_DEV_SKILLS_BY_SKILL_ID = "SELECT dev_id, skill_id, skill_level_id" +
            "FROM dev_skills WHERE skill_id = ?;";
    private static final String SELECT_DEV_SKILLS_BY_SKILL_LEVEL_ID = "SELECT dev_id, skill_id, skill_level_id" +
            "FROM dev_skills WHERE skill_level_id = ?;";


    private static final String INSERT = "INSERT INTO dev_skills (dev_id, skill_id, skill_level_id)" +
            " VALUES (?, ?, ?);";

    private static final String DELETE = "DELETE FROM developers WHERE id=?;";

    private final DatabaseConnectionManager manager;
    private final DevSkillsConverter converter = new DevSkillsConverter();

    public DevSkillsRepository(DatabaseConnectionManager manager) {
        this.manager = manager;
    }


    public DeveloperDAO findByDevId(long id) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEV_SKILLS_BY_DEV_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public DevSkillsDAO findById(long id) {
        return null;
    }

    @Override
    public void create(DevSkillsDAO entity) {

    }

    @Override
    public void update(DevSkillsDAO entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Converter getConverter() {
        return this.converter;
    }
}
