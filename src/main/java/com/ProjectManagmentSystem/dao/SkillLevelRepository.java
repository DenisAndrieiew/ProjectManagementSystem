package com.ProjectManagmentSystem.dao;

import com.ProjectManagmentSystem.dao.model.BrunchDAO;
import com.ProjectManagmentSystem.dao.model.SkillLevelDAO;
import com.ProjectManagmentSystem.dto.BrunchDTO;
import com.ProjectManagmentSystem.jdbc.config.DatabaseConnectionManager;
import com.ProjectManagmentSystem.service.converter.BrunchConverter;
import com.ProjectManagmentSystem.service.converter.Converter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class SkillLevelRepository implements Repository<SkillLevelDAO> {
    private static final String SELECT_BY_ID = "SELECT id, name FROM skill_level WHERE id = ?;";
    private static final String SELECT_BY = "SELECT id, name FROM skill_level WHERE ";
    private static final String INSERT = "INSERT INTO skill_level (id, name) VALUES (?, ?);";
    private static final String UPDATE = "UPDATE skill_level SET name=? WHERE id=?;";
    private static final String DELETE = "DELETE FROM skill_level WHERE id=?;";
    private static final String NEXT_ID = "SELECT MAX(id)+1 FROM skill_level;";
    private final DatabaseConnectionManager manager;
    private final Converter<BrunchDAO, BrunchDTO> converter = new BrunchConverter();

    public SkillLevelRepository(DatabaseConnectionManager manager) {
        this.manager = manager;
    }

    @Override
    public SkillLevelDAO findById(long id) {
        return (SkillLevelDAO) RepositoryUtils.findById(manager, converter, SELECT_BY_ID, id).get(0);
    }

    @Override
    public List<SkillLevelDAO> findByString(String requestField, String requestText) {
        return RepositoryUtils.findByString(manager, converter, SELECT_BY_ID, requestField, requestText).stream()
                .map(dao -> (SkillLevelDAO) dao).collect(Collectors.toList());
    }

    @Override
    public List<SkillLevelDAO> findByNumber(String requestField, long requestNumber) {
        return RepositoryUtils.findByNumber(manager, converter, SELECT_BY_ID, requestField, requestNumber).stream()
                .map(dao -> (SkillLevelDAO) dao).collect(Collectors.toList());
    }

    @Override
    public void create(SkillLevelDAO entity) {
        try (Connection connection = manager.getConnection();
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
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setLong(4, entity.getId());
            statement.setString(1, entity.getName());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Converter getConverter() {
        return null;
    }

    private long getNextId() {
        return RepositoryUtils.getNextId(manager, NEXT_ID);
    }
}
