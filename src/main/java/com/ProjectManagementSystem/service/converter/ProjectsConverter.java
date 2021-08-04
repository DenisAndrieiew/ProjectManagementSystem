package com.ProjectManagementSystem.service.converter;

import com.ProjectManagementSystem.dao.model.ProjectsDAO;
import com.ProjectManagementSystem.dto.ProjectsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProjectsConverter implements Converter<ProjectsDAO, ProjectsDTO> {
    @Override
    public ProjectsDAO fromDTO(ProjectsDTO dto) {
        ProjectsDAO dao = new ProjectsDAO(dto.getName(), dto.getCustomerId(), dto.getCompanyId(),
                dto.getDescription(), dto.getCost(), dto.getBeginDate());
        if (dto.getId() != 0) dao.setId(dto.getId());
        return dao;
    }

    @Override
    public ProjectsDTO toDTO(ProjectsDAO dao) {
        ProjectsDTO dto = new ProjectsDTO(dao.getId(), dao.getName(), dao.getCustomerId(), dao.getCompanyId(),
                dao.getDescription(), dao.getCost(), dao.getBegin_date());
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
            dao.setBegin_date(resultSet.getTimestamp("begin_date").toInstant());
            projectsDAOList.add(dao);
        }
        return projectsDAOList;
    }
}
