package com.ProjectManagementSystem.dao;

import com.ProjectManagementSystem.dao.model.DataAccessObject;
import com.ProjectManagementSystem.jdbc.config.DatabaseConnectionManager;
import com.ProjectManagementSystem.service.converter.Converter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RepositoryUtils {

    public static List<DataAccessObject> findById(DatabaseConnectionManager manager, Converter converter,
                                                  String select, long id) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return converter.fromResultSet(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }



    public static void delete(DatabaseConnectionManager manager, String delete, long id) {
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected static long getNextId(DatabaseConnectionManager manager, String next_id) {
        try (Connection connection = manager.getConnection();
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
