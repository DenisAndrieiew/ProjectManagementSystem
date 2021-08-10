package com.ProjectManagementSystem.dao;

import com.ProjectManagementSystem.dao.model.DeveloperDAO;
import com.ProjectManagementSystem.dao.model.DevelopersInProjectsDAO;
import com.ProjectManagementSystem.dto.DevelopersInProjectsDTO;
import com.ProjectManagementSystem.jdbc.config.DatabaseConnectionManager;
import com.ProjectManagementSystem.service.converter.Converter;
import com.ProjectManagementSystem.service.converter.DevelopersInProjectsConverter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class DevelopersInProjectsRepository implements Repository<DevelopersInProjectsDAO> {
    public static final String NEXT_ID = "SELECT MAX(id)+1 FROM devs_in_project;";
    private static final String SELECT_BY_ID = "SELECT id, dev_id, project_id, " +
            "FROM devs_in_project WHERE id = ?;";
    private static final String SELECT_BY = "SELECT id, dev_id, project_id " +
            "FROM devs_in_project WHERE ";
    private static final String UPDATE = "UPDATE devs_in_project SET dev_id=?, project_id=?, " +
            "WHERE id=?;";
    private static final String INSERT = "INSERT INTO devs_in_project (dev_id, project_id)" +
            " VALUES (?, ?, ?);";
    private static final String DELETE = "DELETE FROM devs_in_project WHERE id=?;";
    private final DataSource dataSource;
    private final Converter<DevelopersInProjectsDAO, DevelopersInProjectsDTO> converter =
            new DevelopersInProjectsConverter();

    public DevelopersInProjectsRepository() {
        this.dataSource = DatabaseConnectionManager.getDataSource();
    }

    @Override
    public DevelopersInProjectsDAO findById(long id) {
        return (DevelopersInProjectsDAO) RepositoryUtils.findById(dataSource, converter, SELECT_BY_ID, id).get(0);
    }
    @Override
    public List<DevelopersInProjectsDAO> findByString(String requestField, String requestText) {
        return RepositoryUtils.findByString(dataSource, converter, SELECT_BY, requestField, requestText).stream()
                .map(dao->(DevelopersInProjectsDAO)dao).collect(Collectors.toList());
    }

    @Override
    public List<DevelopersInProjectsDAO> findByNumber(String requestField, long requestNumber) {
        return RepositoryUtils.findByNumber(dataSource, converter, SELECT_BY, requestField, requestNumber).stream()
                .map(dao->(DevelopersInProjectsDAO)dao).collect(Collectors.toList());
    }


    @Override
    public void create(DevelopersInProjectsDAO entity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            entity.setId(getNextId());
            statement.setLong(1, entity.getId());
            statement.setLong(2, entity.getDeveloperId());
            statement.setLong(3, entity.getProjectId());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(DevelopersInProjectsDAO entity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setLong(4, entity.getId());
            statement.setLong(1, entity.getDeveloperId());
            statement.setLong(2, entity.getProjectId());
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
