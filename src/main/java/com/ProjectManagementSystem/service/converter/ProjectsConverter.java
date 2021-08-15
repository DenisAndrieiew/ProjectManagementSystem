package com.ProjectManagementSystem.service.converter;

import com.ProjectManagementSystem.repository.*;
import com.ProjectManagementSystem.repository.model.DeveloperDAO;
import com.ProjectManagementSystem.repository.model.DevelopersInProjectsDAO;
import com.ProjectManagementSystem.repository.model.ProjectsDAO;
import com.ProjectManagementSystem.dto.ProjectsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProjectsConverter implements Converter<ProjectsDAO, ProjectsDTO> {
    @Override
    public ProjectsDAO fromDTO(ProjectsDTO dto) {
        ProjectsDAO dao = new ProjectsDAO();
        if(Objects.nonNull(dto.getCompany())){
        dao.setCompanyId(new CompanyRepository().findByString("name", dto.getCompany())
                .get(0).getId());}
        if (Objects.nonNull(dto.getCustomer())){
        dao.setCustomerId(new CustomersRepository().findByString("name", dto.getCustomer())
                .get(0).getId());}
        dao.setName(dto.getName());
        dao.setBegin_date(dto.getBeginDate());
        dao.setCost(dto.getCost());
        dao.setDescription(dto.getDescription());
        if (dto.getId() != 0) dao.setId(dto.getId());
        return dao;
    }

    @Override
    public ProjectsDTO toDTO(ProjectsDAO dao) {
        ProjectsDTO dto = new ProjectsDTO();
        dto.setId(dao.getId());
        dto.setName(dao.getName());
        dto.setBeginDate(dao.getBegin_date());
        dto.setDescription(dao.getDescription());
        dto.setCompany(new CompanyRepository().findById(dao.getCompanyId()).getName());
        dto.setCustomer(new CustomersRepository().findById(dao.getCustomerId()).getName());
        dto.setCost(dao.getCost());
        List<String> developers = dao.getDevelopers().stream().map(id -> {
            DeveloperDAO dev = new DeveloperRepository().findById(id);
            return String.format("%s %s", dev.getFirstName(), dev.getLastName());
        })
                .collect(Collectors.toList());
        dto.setDevelopers(developers);
        return dto;
    }

    @Override
    public List<ProjectsDAO> fromResultSet(ResultSet resultSet) throws SQLException {
        List<ProjectsDAO> projectsDAOList = new LinkedList<>();
        while (resultSet.next()) {
            ProjectsDAO dao = new ProjectsDAO();
            dao.setId(resultSet.getLong("id"));
            dao.setName(resultSet.getString("name"));
            dao.setCustomerId(resultSet.getLong("customer_id"));
            dao.setCompanyId(resultSet.getLong("company_id"));
            dao.setDescription(resultSet.getString("description"));
            dao.setBegin_date(resultSet.getTimestamp("begin_date").toInstant());
            projectsDAOList.add(dao);
            List<DevelopersInProjectsDAO> dip = new DevelopersInProjectsRepository()
                    .findByNumber("project_id", dao.getId());
            if (Objects.nonNull(dip)) {
                dao.setDevelopers(dip.stream().map(d -> d.getDeveloperId()).collect(Collectors.toList()));
            }
            dao.setCost(new ProjectsRepository().getCost(dao.getId()));
        }
        return projectsDAOList;
    }
}
