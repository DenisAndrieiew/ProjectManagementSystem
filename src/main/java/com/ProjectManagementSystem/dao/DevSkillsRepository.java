package com.ProjectManagementSystem.dao;

import com.ProjectManagementSystem.dao.model.DevSkillsDAO;
import com.ProjectManagementSystem.dto.DevSkillsDTO;
import com.ProjectManagementSystem.jdbc.config.DatabaseConnectionManager;
import com.ProjectManagementSystem.service.converter.Converter;
import com.ProjectManagementSystem.service.converter.DevSkillsConverter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class DevSkillsRepository implements Repository<DevSkillsDAO> {
    public static final String NEXT_ID = "SELECT MAX(id)+1 FROM dev_skills;";
    private static final String SELECT_BY_ID = "SELECT id, dev_id, skill_id, skill_level" +
            "FROM dev_skills WHERE id = ?;";
    private static final String SELECT_BY = "SELECT id, dev_id, skill_id, skill_level " +
            "FROM dev_skills WHERE ";
    private static final String UPDATE = "UPDATE dev_skills SET dev_id=?, skill_id=?, " +
            "skill_level=? WHERE id=?;";
    private static final String INSERT = "INSERT INTO dev_skills (dev_id, skill_id, skill_level)" +
            " VALUES (?, ?, ?);";
    private static final String DELETE = "DELETE FROM dev_skills WHERE id=?;";
    private final DataSource dataSource;
    private final Converter<DevSkillsDAO, DevSkillsDTO> converter = new DevSkillsConverter();

    public DevSkillsRepository() {
        this.dataSource = DatabaseConnectionManager.getDataSource();
    }


    @Override
    public DevSkillsDAO findById(long id) {
        return (DevSkillsDAO) RepositoryUtils.findById(dataSource, converter, SELECT_BY_ID, id).get(0);
    }

    @Override
    public List<DevSkillsDAO> findByString(String requestField, String requestText) {
        return RepositoryUtils.findByString(dataSource, converter, SELECT_BY, requestField, requestText).stream()
                .map(dao -> (DevSkillsDAO) dao).collect(Collectors.toList());
    }

    @Override
    public List<DevSkillsDAO> findByNumber(String requestField, long requestNumber) {
        return RepositoryUtils.findByNumber(dataSource, converter, SELECT_BY, requestField, requestNumber).stream()
                .map(dao -> (DevSkillsDAO) dao).collect(Collectors.toList());
    }


    @Override
    public void create(DevSkillsDAO entity) {
        try (Connection connection = dataSource.getConnection();
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
        try (Connection connection = dataSource.getConnection();
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
        RepositoryUtils.delete(dataSource, DELETE, id);
    }

    @Override
    public Converter getConverter() {
        return this.converter;
    }


    private long getNextId() {
        return RepositoryUtils.getNextId(dataSource, NEXT_ID);
    }
}
