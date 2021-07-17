package com.ProjectManagmentSystem.service.converter;

import com.ProjectManagmentSystem.dao.model.ProjectsDAO;
import com.ProjectManagmentSystem.dto.ProjectsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProjectsConverter implements Converter<ProjectsDAO, ProjectsDTO> {
    @Override
    public ProjectsDAO fromDTO(ProjectsDTO dto) {
        ProjectsDAO dao = new ProjectsDAO(dto.getName(), dto.getCustomerId(), dto.getCompanyId(),
                dto.getDescription(), dto.getCost());
        if (dto.getId() != 0) dao.setId(dto.getId());
        return dao;
    }

    @Override
    public ProjectsDTO toDTO(ProjectsDAO dao) {
        ProjectsDTO dto = new ProjectsDTO(dao.getId(), dao.getName(), dao.getCustomerId(), dao.getCompanyId(),
                dao.getDescription(), dao.getCost());
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
            dao.setCost(resultSet.getInt("cost"));
            projectsDAOList.add(dao);
        }
        return projectsDAOList;
    }
}
