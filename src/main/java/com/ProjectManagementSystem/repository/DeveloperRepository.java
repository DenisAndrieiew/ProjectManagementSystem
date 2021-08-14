package com.ProjectManagementSystem.repository;

import com.ProjectManagementSystem.dto.DeveloperDTO;
import com.ProjectManagementSystem.jdbc.config.DatabaseConnectionManager;
import com.ProjectManagementSystem.repository.model.DevSkillsDAO;
import com.ProjectManagementSystem.repository.model.DeveloperDAO;
import com.ProjectManagementSystem.repository.model.DevelopersInProjectsDAO;
import com.ProjectManagementSystem.repository.model.ProjectsDAO;
import com.ProjectManagementSystem.service.converter.Converter;
import com.ProjectManagementSystem.service.converter.DeveloperConverter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class DeveloperRepository implements EntityRepository<DeveloperDAO> {
    private static final String SELECT_BY_ID = "SELECT id, first_name, last_name, age," +
            " dev_sex, comments, salary FROM developers WHERE id = ?;";
    private static final String SELECT_BY = "SELECT id, first_name, last_name, age," +
            " dev_sex, comments, salary FROM developers WHERE ";
    private static final String INSERT = "INSERT INTO developers (id, first_name, last_name, " +
            "age, dev_sex, comments, salary) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE developers SET first_name=?, last_name=?, " +
            "age=?, dev_sex=?, comments=?, salary=? WHERE id=?;";
    private static final String DELETE = "DELETE FROM developers WHERE id = ?; ";
    private static final String NEXT_ID = "SELECT MAX(id)+1 FROM developers;";
    private static final String SELECT_ALL = "SELECT id, first_name, last_name, age," +
            " dev_sex, comments, salary FROM developers";

    private final DataSource dataSource;
    private final Converter<DeveloperDAO, DeveloperDTO> converter = new DeveloperConverter();

    public DeveloperRepository() {
        this.dataSource = DatabaseConnectionManager.getDataSource();
    }

    @Override
    public DeveloperDAO findById(long id) {
        return (DeveloperDAO) RepositoryUtils.findById(dataSource, converter, SELECT_BY_ID, id).get(0);
    }

    @Override
    public List<DeveloperDAO> findByString(String requestField, String requestText) {
        return RepositoryUtils.findByString(dataSource, converter, SELECT_BY, requestField, requestText).stream()
                .map(dao -> (DeveloperDAO) dao).collect(Collectors.toList());

    }

    @Override
    public List<DeveloperDAO> findByNumber(String requestField, long requestNumber) {
        return RepositoryUtils.findByNumber(dataSource, converter, SELECT_BY, requestField, requestNumber).stream()
                .map(dao -> (DeveloperDAO) dao).collect(Collectors.toList());

    }

    @Override
    public List<DeveloperDAO> findAll() {
        return RepositoryUtils.findAll(dataSource, converter, SELECT_ALL).stream()
                .map(dao -> (DeveloperDAO) dao).collect(Collectors.toList());
    }

    @Override
    public void create(DeveloperDAO entity) {
        try (Connection connection = dataSource.getConnection();
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
        DevelopersInProjectsRepository developersInProjectsRepository = new DevelopersInProjectsRepository();
        List<ProjectsDAO> projects = entity.getProjects();
        projects.forEach(p -> developersInProjectsRepository
                .create(new DevelopersInProjectsDAO(entity.getId(), p.getId())));
        ProjectsRepository projectsRepository = new ProjectsRepository();
        projects.forEach(p -> projectsRepository.updateCost(p.getId(), entity.getSalary()));
        if (Objects.nonNull(entity.getSkillLevels())) {
            DevSkillsRepository devSkillsRepository = new DevSkillsRepository();
            entity.getSkillLevels().forEach((skill, level) -> devSkillsRepository
                    .create(new DevSkillsDAO(entity.getId(), skill, level)));
        }
    }

    @Override
    public void update(DeveloperDAO entity) {
        try (Connection connection = dataSource.getConnection();
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
        DeveloperDAO developerDAO = new DeveloperRepository().findById(id);
        List<DevelopersInProjectsDAO> dip;
        DevelopersInProjectsRepository dipRepository = new DevelopersInProjectsRepository();
        ProjectsRepository projectsRepository = new ProjectsRepository();
        dip = dipRepository.findByNumber("dev_id", id);
        if (Objects.nonNull(dip)) {
            dip.forEach(dip1 -> projectsRepository.updateCost(dip1.getProjectId(), -developerDAO.getSalary()));
            RepositoryUtils.delete(dataSource, DELETE, id);
            dipRepository.deleteByUser(id);

        }
        if (!developerDAO.getSkillLevels().isEmpty()){
            new DevSkillsRepository().deleteByUser(id);
        }
        RepositoryUtils.delete(dataSource, DELETE, id);
    }


    private long getNextId() {
        return RepositoryUtils.getNextId(dataSource, NEXT_ID);
    }
}
