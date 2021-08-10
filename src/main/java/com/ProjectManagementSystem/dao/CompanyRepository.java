package com.ProjectManagementSystem.dao;

import com.ProjectManagementSystem.dao.model.CompanyDAO;
import com.ProjectManagementSystem.dao.model.DeveloperDAO;
import com.ProjectManagementSystem.dto.CompanyDTO;
import com.ProjectManagementSystem.jdbc.config.DatabaseConnectionManager;
import com.ProjectManagementSystem.service.converter.CompanyConverter;
import com.ProjectManagementSystem.service.converter.Converter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyRepository implements EntityRepository<CompanyDAO> {

    private static final String SELECT_BY_ID = "SELECT id, name FROM company WHERE id = ?;";
    private static final String SELECT_BY = "SELECT id, name FROM company WHERE ";
    private static final String INSERT = "INSERT INTO company (id, name) VALUES (?, ?);";
    private static final String UPDATE = "UPDATE company SET name=? WHERE id=?;";
    private static final String DELETE = "DELETE FROM company WHERE id=?;";
    private static final String NEXT_ID = "SELECT MAX(id)+1 FROM company;";
    private static final String SELECT_ALL= "SELECT id, name FROM company";
    private final DatabaseConnectionManager manager;
    private final Converter<CompanyDAO, CompanyDTO> converter = new CompanyConverter();

    public CompanyRepository(DatabaseConnectionManager manager) {
        this.manager = manager;
    }

    @Override
    public CompanyDAO findById(long id) {
        return (CompanyDAO) RepositoryUtils.findById(manager, converter, SELECT_BY_ID, id).get(0);
    }

    @Override
    public List<CompanyDAO> findByString(String requestField, String requestText) {
        return RepositoryUtils.findByString(manager, converter, SELECT_BY, requestField, requestText).
                stream().map(dao -> (CompanyDAO) dao).collect(Collectors.toList());
    }

    @Override
    public List<CompanyDAO> findByNumber(String requestField, long requestNumber) {
        return RepositoryUtils.findByNumber(manager, converter, SELECT_BY, requestField, requestNumber).
                stream().map(dao -> (CompanyDAO) dao).collect(Collectors.toList());
    }

    @Override
    public void create(CompanyDAO entity) {
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
    public void update(CompanyDAO entity) {
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setLong(2, entity.getId());
            statement.setString(1, entity.getName());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public List<CompanyDAO> findAll() {
        return RepositoryUtils.findAll(manager, converter, SELECT_ALL).stream()
                .map(dao -> (CompanyDAO) dao).collect(Collectors.toList());
    }
    @Override
    public void delete(long id) {
        RepositoryUtils.delete(manager, DELETE, id);
    }

    @Override
    public Converter getConverter() {
        return null;
    }

    private long getNextId() {
        return RepositoryUtils.getNextId(manager, NEXT_ID);
    }
}
