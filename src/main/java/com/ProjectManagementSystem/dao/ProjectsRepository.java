package com.ProjectManagementSystem.dao;

import com.ProjectManagementSystem.dao.model.ProjectsDAO;
import com.ProjectManagementSystem.dto.ProjectsDTO;
import com.ProjectManagementSystem.jdbc.config.DatabaseConnectionManager;
import com.ProjectManagementSystem.service.converter.Converter;
import com.ProjectManagementSystem.service.converter.ProjectsConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectsRepository implements Repository<ProjectsDAO> {
    private static final String SELECT_BY_ID = "SELECT id, name, customer_id, company_id," +
            "description, cost FROM projects WHERE id = ?;";
    private static final String INSERT = "INSERT INTO projects (id, name, customer_id, " +
            "+ company_id, description, cost) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE projects SET name=?, customer_id=?, " +
            "company_id=?, description=?, cost=? WHERE id=?;";
    private static final String DELETE = "DELETE FROM projects WHERE id=?;";
    private static final String NEXT_ID = "SELECT MAX(id)+1 FROM projects;";

    private final DatabaseConnectionManager manager;
    private final Converter<ProjectsDAO, ProjectsDTO> converter = new ProjectsConverter();

    public ProjectsRepository(DatabaseConnectionManager manager) {
        this.manager = manager;
    }

    @Override
    public ProjectsDAO findById(long id) {
        return (ProjectsDAO) RepositoryUtils.findById(manager, converter, SELECT_BY_ID, id).get(0);
    }


    @Override
    public void create(ProjectsDAO entity) {
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            entity.setId(getNextId());
            statement.setLong(1, entity.getId());
            statement.setString(2, entity.getName());
            statement.setLong(3, entity.getCustomerId());
            statement.setLong(4, entity.getCompanyId());
            statement.setString(5, entity.getDescription());
            statement.setInt(6, entity.getCost());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(ProjectsDAO entity) {
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setLong(7, entity.getId());
            statement.setString(1, entity.getName());
            statement.setLong(2, entity.getCustomerId());
            statement.setLong(3, entity.getCompanyId());
            statement.setString(4, entity.getDescription());
            statement.setInt(5, entity.getCost());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Converter getConverter() {
        return converter;
    }


    @Override
    public void delete(long id) {
        RepositoryUtils.delete(manager, DELETE, id);
    }


    private long getNextId() {
        return RepositoryUtils.getNextId(manager, NEXT_ID);
    }

}
