package com.ProjectManagementSystem.repository;

import com.ProjectManagementSystem.repository.model.ProjectsDAO;
import com.ProjectManagementSystem.dto.ProjectsDTO;
import com.ProjectManagementSystem.jdbc.config.DatabaseConnectionManager;
import com.ProjectManagementSystem.service.converter.Converter;
import com.ProjectManagementSystem.service.converter.ProjectsConverter;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectsRepository implements EntityRepository<ProjectsDAO> {
    private static final String SELECT_BY_ID = "SELECT id, name, customer_id, company_id, " +
            "description, begin_date FROM projects WHERE id = ?;";
    private static final String SELECT_BY = "SELECT id, name, customer_id, company_id," +
            "description, begin_date FROM projects WHERE ";
    private static final String INSERT = "INSERT INTO projects (id, name, customer_id, " +
            "company_id, description, begin_date) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE projects SET name=?, customer_id=?, " +
            "company_id=?, description=?, begin_date=? WHERE id=?;";
    private static final String DELETE = "DELETE FROM projects WHERE id=?;";
    private static final String NEXT_ID = "SELECT MAX(id)+1 FROM projects;";
    private static final String SELECT_ALL = "SELECT id, name, customer_id, company_id, "+
            "description, begin_date FROM projects;";
    private static final String UPDATE_COST = "UPDATE projects SET cost=cost+? WHERE id=?";
    private static final String GET_COST="SELECT SUM(d.salary) as cost from developers as d "+
            "INNER JOIN devs_in_project as dp on d.id=dp.dev_id WHERE dp.project_id=?";


    private final DataSource dataSource;
    private final Converter<ProjectsDAO, ProjectsDTO> converter = new ProjectsConverter();

    public ProjectsRepository() {
        this.dataSource = DatabaseConnectionManager.getDataSource();
    }

    @Override
    public ProjectsDAO findById(long id) {
        List<ProjectsDAO> list = RepositoryUtils.findById(dataSource, converter, SELECT_BY_ID, id)
                .stream().map((entity)->(ProjectsDAO) entity).collect(Collectors.toList());

        return list.get(0);
    }

    @Override
    public List<ProjectsDAO> findByString(String requestField, String requestText) {
        return RepositoryUtils.findByString(dataSource, converter, SELECT_BY, requestField, requestText).stream()
                .map(dao -> (ProjectsDAO) dao).collect(Collectors.toList());

    }

    @Override
    public List<ProjectsDAO> findByNumber(String requestField, long requestNumber) {
        return RepositoryUtils.findByNumber(dataSource, converter, SELECT_BY, requestField, requestNumber).stream()
                .map(dao -> (ProjectsDAO) dao).collect(Collectors.toList());

    }

    @Override
    public void create(ProjectsDAO entity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            entity.setId(getNextId());
            statement.setLong(1, entity.getId());
            statement.setString(2, entity.getName());
            statement.setLong(3, entity.getCustomerId());
            statement.setLong(4, entity.getCompanyId());
            statement.setString(5, entity.getDescription());
            statement.setTimestamp(6, Timestamp.from(entity.getBegin_date()));
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(ProjectsDAO entity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setLong(6, entity.getId());
            statement.setString(1, entity.getName());
            statement.setLong(2, entity.getCustomerId());
            statement.setLong(3, entity.getCompanyId());
            statement.setString(4, entity.getDescription());
            statement.setTimestamp(5, Timestamp.from(entity.getBegin_date()));
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
        ProjectsDAO entity = new ProjectsRepository().findById(id);
        if (entity.getDevelopers().size()>0){
            new DevelopersInProjectsRepository().deleteByProject(id);
        }
        RepositoryUtils.delete(dataSource, DELETE, id);
    }


    private long getNextId() {
        return RepositoryUtils.getNextId(dataSource, NEXT_ID);
    }

    public List<ProjectsDAO> findAll(){
        return RepositoryUtils.findAll(dataSource, getConverter(), SELECT_ALL).stream().map(entity->(ProjectsDAO) entity)
                .collect(Collectors.toList());
    }
    public int getCost(long id){
        int cost = 0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_COST)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) cost = resultSet.getInt("cost");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cost;
    }

}
