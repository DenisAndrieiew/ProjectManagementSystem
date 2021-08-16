package com.ProjectManagementSystem.repository;

import com.ProjectManagementSystem.repository.model.CompanyDAO;
import com.ProjectManagementSystem.dto.CompanyDTO;
import com.ProjectManagementSystem.jdbc.config.DatabaseConnectionManager;
import com.ProjectManagementSystem.repository.model.ProjectsDAO;
import com.ProjectManagementSystem.service.converter.CompanyConverter;
import com.ProjectManagementSystem.service.converter.Converter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CompanyRepository implements EntityRepository<CompanyDAO> {

    private static final String SELECT_BY_ID = "SELECT id, name FROM company WHERE id = ?;";
    private static final String SELECT_BY = "SELECT id, name FROM company WHERE ";
    private static final String INSERT = "INSERT INTO company (id, name) VALUES (?, ?);";
    private static final String UPDATE = "UPDATE company SET name=? WHERE id=?;";
    private static final String DELETE = "DELETE FROM company WHERE id=?;";
    private static final String NEXT_ID = "SELECT MAX(id)+1 FROM company;";
    private static final String SELECT_ALL= "SELECT id, name FROM company";
    private final DataSource dataSource;
    private final Converter<CompanyDAO, CompanyDTO> converter = new CompanyConverter();

    public CompanyRepository() {
       dataSource=DatabaseConnectionManager.getDataSource();
    }

    @Override
    public CompanyDAO findById(long id) {
        return (CompanyDAO) RepositoryUtils.findById(dataSource, converter, SELECT_BY_ID, id).get(0);
    }

    @Override
    public List<CompanyDAO> findByString(String requestField, String requestText) {
        return RepositoryUtils.findByString(dataSource, converter, SELECT_BY, requestField, requestText).
                stream().map(dao -> (CompanyDAO) dao).collect(Collectors.toList());
    }

    @Override
    public List<CompanyDAO> findByNumber(String requestField, long requestNumber) {
        return RepositoryUtils.findByNumber(dataSource, converter, SELECT_BY, requestField, requestNumber).
                stream().map(dao -> (CompanyDAO) dao).collect(Collectors.toList());
    }

    @Override
    public void create(CompanyDAO entity) {
        try (Connection connection = dataSource.getConnection();
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
        try (Connection connection = dataSource.getConnection();
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
        return RepositoryUtils.findAll(dataSource, converter, SELECT_ALL).stream()
                .map(dao -> (CompanyDAO) dao).collect(Collectors.toList());
    }
    @Override
    public void delete(long id) {
        ProjectsRepository projectsRepository = new ProjectsRepository();
        List<ProjectsDAO> projects = projectsRepository.findByNumber("company_id", id);
        if (Objects.nonNull(projects)){
            projects.forEach(dao->projectsRepository.delete(dao.getId()));
        }
        RepositoryUtils.delete(dataSource, DELETE, id);
    }

    @Override
    public Converter getConverter() {
        return converter;
    }

    private long getNextId() {
        return RepositoryUtils.getNextId(dataSource, NEXT_ID);
    }
}
