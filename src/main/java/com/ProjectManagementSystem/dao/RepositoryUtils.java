package com.ProjectManagementSystem.dao;

import com.ProjectManagementSystem.dao.model.DataAccessObject;
import com.ProjectManagementSystem.service.converter.Converter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RepositoryUtils {

    public static List<DataAccessObject> findById(DataSource dataSource, Converter converter,
                                                  String select, long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return converter.fromResultSet(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static List<DataAccessObject> findByNumber(DataSource dataSource, Converter converter,
                                                      String select, String requestField, long requestNumber) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(select + requestField + "=?;")) {
            preparedStatement.setLong(1, requestNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            return converter.fromResultSet(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static List<DataAccessObject> findByString(DataSource dataSource, Converter converter,
                                                      String select, String requestField, String requestText) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(select + requestField + "=?;")) {
            preparedStatement.setString(1, requestText);
            ResultSet resultSet = preparedStatement.executeQuery();
            return converter.fromResultSet(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }


    public static void delete(DataSource dataSource, String delete, long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static List<DataAccessObject> findAll(DataSource dataSource, Converter converter, String select) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return converter.fromResultSet(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }


    protected static long getNextId(DataSource dataSource, String next_id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(next_id)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
}
