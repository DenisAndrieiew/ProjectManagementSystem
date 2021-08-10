package com.ProjectManagementSystem.dao;

import com.ProjectManagementSystem.dao.model.CustomersDAO;
import com.ProjectManagementSystem.dao.model.DeveloperDAO;
import com.ProjectManagementSystem.dto.CustomersDTO;
import com.ProjectManagementSystem.jdbc.config.DatabaseConnectionManager;
import com.ProjectManagementSystem.service.converter.Converter;
import com.ProjectManagementSystem.service.converter.CustomersConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class CustomersRepository implements EntityRepository<CustomersDAO> {

    private static final String SELECT_BY_ID = "SELECT id, name FROM customers WHERE id = ?;";
    private static final String SELECT_BY = "SELECT id, name FROM customers WHERE ";
    private static final String INSERT = "INSERT INTO customers (id, name) VALUES (?, ?);";
    private static final String UPDATE = "UPDATE customers SET name=? WHERE id=?;";
    private static final String DELETE = "DELETE FROM customers WHERE id=?;";
    private static final String NEXT_ID = "SELECT MAX(id)+1 FROM customers;";
    private final static String SELECT_ALL = "SELECT id, name FROM customers";
    private final DatabaseConnectionManager manager;
    private final Converter<CustomersDAO, CustomersDTO> converter = new CustomersConverter();

    public CustomersRepository(DatabaseConnectionManager manager) {
        this.manager = manager;
    }

    @Override
    public CustomersDAO findById(long id) {
        return (CustomersDAO) RepositoryUtils.findById(manager, converter, SELECT_BY_ID, id).get(0);
    }

    @Override
    public List<CustomersDAO> findByString(String requestField, String requestText) {
        return RepositoryUtils.findByString(manager, converter, SELECT_BY, requestField, requestText)
                .stream().map(dao -> (CustomersDAO) dao).collect(Collectors.toList());
    }

    @Override
    public List<CustomersDAO> findByNumber(String requestField, long requestNumber) {
        return RepositoryUtils.findByNumber(manager, converter, SELECT_BY, requestField, requestNumber)
                .stream().map(dao -> (CustomersDAO) dao).collect(Collectors.toList());

    }
    @Override
    public List<CustomersDAO> findAll() {
        return RepositoryUtils.findAll(manager, converter, SELECT_ALL).stream()
                .map(dao -> (CustomersDAO) dao).collect(Collectors.toList());
    }

    @Override
    public void create(CustomersDAO entity) {
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
    public void update(CustomersDAO entity) {
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
