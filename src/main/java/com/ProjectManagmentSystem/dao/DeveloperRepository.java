package com.ProjectManagmentSystem.dao;

import com.ProjectManagmentSystem.dao.model.DeveloperDAO;
import com.ProjectManagmentSystem.dto.DeveloperDTO;
import com.ProjectManagmentSystem.jdbc.config.DatabaseConnectionManager;
import com.ProjectManagmentSystem.service.converter.Converter;
import com.ProjectManagmentSystem.service.converter.DeveloperConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DeveloperRepository implements Repository<DeveloperDAO> {
    private static final String SELECT_DEVELOPER_BY_ID = "SELECT id, first_name, last_name, age," +
            " dev_sex, comments, salary FROM developers WHERE id = ?;";
    private static final String INSERT = "INSERT INTO developers (id, first_name, last_name, " +
            "age, dev_sex, comments, salary) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE developers SET first_name=?, last_name=?, " +
            "age=?, dev_sex=?, comments=?, salary=? WHERE id=?;";
    private static final String DELETE = "DELETE FROM developers WHERE id=?;";

    private final DatabaseConnectionManager manager;
    private final Converter<DeveloperDAO, DeveloperDTO> converter = new DeveloperConverter();

    public DeveloperRepository(DatabaseConnectionManager manager) {
        this.manager = manager;
    }

    @Override
    public DeveloperDAO findById(long id) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEVELOPER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return converter.fromResultSet(resultSet).get(0);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public void create(DeveloperDAO entity) {
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
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
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
