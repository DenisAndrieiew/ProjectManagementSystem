package com.ProjectManagmentSystem.dao;

import com.ProjectManagmentSystem.dao.model.BrunchDAO;
import com.ProjectManagmentSystem.dao.model.DeveloperDAO;
import com.ProjectManagmentSystem.dto.BrunchDTO;
import com.ProjectManagmentSystem.jdbc.config.DatabaseConnectionManager;
import com.ProjectManagmentSystem.service.converter.BrunchConverter;
import com.ProjectManagmentSystem.service.converter.Converter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class BrunchRepository implements Repository<BrunchDAO> {
    private static final String SELECT_BY_ID = "SELECT id, name FROM brunch WHERE id = ?;";
    private static final String SELECT_BY = "SELECT id, name FROM brunch WHERE ";
    private static final String INSERT = "INSERT INTO brunch (id, name) VALUES (?, ?);";
    private static final String UPDATE = "UPDATE brunch SET name=? WHERE id=?;";
    private static final String DELETE = "DELETE FROM brunch WHERE id=?;";
    private static final String NEXT_ID = "SELECT MAX(id)+1 FROM developers;";
    private final DatabaseConnectionManager manager;
    private final Converter<BrunchDAO, BrunchDTO> converter = new BrunchConverter();

    public BrunchRepository(DatabaseConnectionManager manager) {
        this.manager = manager;
    }

    @Override
    public BrunchDAO findById(long id) {
        return (BrunchDAO) RepositoryUtils.findById(manager, converter, SELECT_BY_ID, id).get(0);
           }

    @Override
    public List<BrunchDAO> findByString(String requestField, String requestText) {
        return RepositoryUtils.findByString(manager, converter, SELECT_BY_ID, requestField, requestText).stream()
                .map(dao->(BrunchDAO)dao).collect(Collectors.toList());
    }

    @Override
    public List<BrunchDAO> findByNumber(String requestField, long requestNumber) {
        return RepositoryUtils.findByNumber(manager, converter, SELECT_BY_ID, requestField, requestNumber).stream()
                .map(dao->(BrunchDAO)dao).collect(Collectors.toList());
    }

    @Override
    public void create(BrunchDAO entity) {
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setLong(1, getNextId());
            statement.setString(2, entity.getBrunch().toString());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(BrunchDAO entity) {
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setLong(4, entity.getId());
            statement.setString(1, entity.getBrunch().toString());
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
