package com.ProjectManagementSystem.dao;

import com.ProjectManagementSystem.dao.model.DevSkillsDAO;
import com.ProjectManagementSystem.dto.DevSkillsDTO;
import com.ProjectManagementSystem.jdbc.config.DatabaseConnectionManager;
import com.ProjectManagementSystem.service.converter.Converter;
import com.ProjectManagementSystem.service.converter.DevSkillsConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class DevSkillsRepository implements Repository<DevSkillsDAO> {
    public static final String NEXT_ID = "SELECT MAX(id)+1 FROM dev_skills;";
    private static final String SELECT_BY_ID = "SELECT id, dev_id, skill_id, skill_level_id" +
            "FROM dev_skills WHERE id = ?;";
    private static final String UPDATE = "UPDATE dev_skills SET dev_id=?, skill_id=?, " +
            "skill_level_id=? WHERE id=?;";
    private static final String INSERT = "INSERT INTO dev_skills (dev_id, skill_id, skill_level_id)" +
            " VALUES (?, ?, ?);";
    private static final String DELETE = "DELETE FROM dev_skills WHERE id=?;";
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
    public void create(DevSkillsDAO entity) {
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            entity.setId(getNextId());
            statement.setLong(1, entity.getId());
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
