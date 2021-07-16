package com.ProjectManagmentSystem.dao;

import com.ProjectManagmentSystem.dao.model.DeveloperDAO;
import com.ProjectManagmentSystem.dto.DeveloperDTO;
import com.ProjectManagmentSystem.jdbc.config.DatabaseConnectionManager;
import com.ProjectManagmentSystem.service.converter.Converter;
import com.ProjectManagmentSystem.service.converter.DeveloperConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;


public class DeveloperRepository implements Repository<DeveloperDAO> {
    private static final String SELECT_BY_ID = "SELECT id, first_name, last_name, age," +
            " dev_sex, comments, salary FROM developers WHERE id = ?;";
    private static final String SELECT_BY = "SELECT id, first_name, last_name, age," +
            " dev_sex, comments, salary FROM developers WHERE ";
    private static final String INSERT = "INSERT INTO developers (id, first_name, last_name, " +
            "age, dev_sex, comments, salary) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE developers SET first_name=?, last_name=?, " +
            "age=?, dev_sex=?, comments=?, salary=? WHERE id=?;";
    private static final String DELETE = "DELETE FROM developers WHERE id=?;";
    private static final String NEXT_ID = "SELECT MAX(id)+1 FROM developers;";

    private final DatabaseConnectionManager manager;
    private final Converter<DeveloperDAO, DeveloperDTO> converter = new DeveloperConverter();

    public DeveloperRepository(DatabaseConnectionManager manager) {
        this.manager = manager;
    }

    @Override
    public DeveloperDAO findById(long id) {
        return (DeveloperDAO) RepositoryUtils.findById(manager, converter, SELECT_BY_ID, id).get(0);
    }

    @Override
    public List<DeveloperDAO> findByString(String requestField, String requestText) {
        return RepositoryUtils.findByString(manager, converter, SELECT_BY, requestField, requestText).stream()
                .map(dao -> (DeveloperDAO) dao).collect(Collectors.toList());

    }

    @Override
    public List<DeveloperDAO> findByNumber(String requestField, long requestNumber) {
        return RepositoryUtils.findByNumber(manager, converter, SELECT_BY, requestField, requestNumber).stream()
                .map(dao -> (DeveloperDAO) dao).collect(Collectors.toList());

    }

    @Override
    public void create(DeveloperDAO entity) {
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            entity.setId(getNextId());
            statement.setLong(1, entity.getId());
            statement.setString(2, entity.getFirstName());
            statement.setString(3, entity.getLastName());
            statement.setInt(4, entity.getAge());
            statement.setString(5, entity.getSex().toString());
            statement.setString(6, entity.getComments());
            statement.setInt(7, entity.getSalary());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(DeveloperDAO entity) {
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setLong(7, entity.getId());
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setInt(3, entity.getAge());
            statement.setString(4, entity.getSex().toString());
            statement.setString(5, entity.getComments());
            statement.setInt(6, entity.getSalary());
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
