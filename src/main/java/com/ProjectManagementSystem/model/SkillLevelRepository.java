package com.ProjectManagementSystem.model;

import com.ProjectManagementSystem.model.dao.SkillLevelDAO;
import com.ProjectManagementSystem.dto.SkillLevelDTO;
import com.ProjectManagementSystem.config.config.DatabaseConnectionManager;
import com.ProjectManagementSystem.service.converter.Converter;
import com.ProjectManagementSystem.service.converter.SkillLevelConverter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class SkillLevelRepository implements EntityRepository<SkillLevelDAO> {
    private static final String SELECT_BY_ID = "SELECT id, name FROM skill_level WHERE id = ?;";
    private static final String SELECT_BY = "SELECT id, name FROM skill_level WHERE ";
    private static final String INSERT = "INSERT INTO skill_level (id, name) VALUES (?, ?);";
    private static final String UPDATE = "UPDATE skill_level SET name=? WHERE id=?;";
    private static final String DELETE = "DELETE FROM skill_level WHERE id=?;";
    private static final String NEXT_ID = "SELECT MAX(id)+1 FROM skill_level;";
    private static final String SELECT_ALL = "SELECT id, name FROM skill_level";
    private final DataSource dataSource;
    private final Converter<SkillLevelDAO, SkillLevelDTO> converter = new SkillLevelConverter();

    public SkillLevelRepository() {
        this.dataSource=DatabaseConnectionManager.getDataSource();
    }

    @Override
    public SkillLevelDAO findById(long id) {
        return (SkillLevelDAO) RepositoryUtils.findById(dataSource, converter, SELECT_BY_ID, id).get(0);
    }

    @Override
    public List<SkillLevelDAO> findByString(String requestField, String requestText) {
        return RepositoryUtils.findByString(dataSource, converter, SELECT_BY, requestField, requestText).stream()
                .map(dao -> (SkillLevelDAO) dao).collect(Collectors.toList());
    }

    @Override
    public List<SkillLevelDAO> findByNumber(String requestField, long requestNumber) {
        return RepositoryUtils.findByNumber(dataSource, converter, SELECT_BY, requestField, requestNumber).stream()
                .map(dao -> (SkillLevelDAO) dao).collect(Collectors.toList());
    }

    @Override
    public void create(SkillLevelDAO entity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            entity.setId(getNextId());
            statement.setLong(1, entity.getId());
            statement.setString(2, entity.getName());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(SkillLevelDAO entity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setLong(4, entity.getId());
            statement.setString(1, entity.getName());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {RepositoryUtils.delete(dataSource, DELETE, id);}

    @Override
    public Converter getConverter() {
        return converter;
    }

    private long getNextId() {
        return RepositoryUtils.getNextId(dataSource, NEXT_ID);
    }
    @Override
    public List<SkillLevelDAO> findAll() {
        return RepositoryUtils.findAll(dataSource, converter, SELECT_ALL).stream()
                .map(dao -> (SkillLevelDAO) dao).collect(Collectors.toList());
    }
}
