package com.ProjectManagementSystem.service.converter;

import com.ProjectManagementSystem.repository.model.DevelopersInProjectsDAO;
import com.ProjectManagementSystem.dto.DevelopersInProjectsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DevelopersInProjectsConverter implements
        Converter<DevelopersInProjectsDAO, DevelopersInProjectsDTO> {

    public DevelopersInProjectsDAO fromDTO(DevelopersInProjectsDTO dto) {
        DevelopersInProjectsDAO dao = new DevelopersInProjectsDAO(dto.getDevId(), dto.getProjectId());
        if (dto.getId() != 0) dao.setId(dto.getId());
        return dao;
    }


    public DevelopersInProjectsDTO toDTO(DevelopersInProjectsDAO dao) {
        DevelopersInProjectsDTO dto = new DevelopersInProjectsDTO(
                dao.getId(), dao.getDeveloperId(), dao.getProjectId());
        return dto;
    }


    public List<DevelopersInProjectsDAO> fromResultSet(ResultSet resultSet) throws SQLException {
        List<DevelopersInProjectsDAO> developersInProjectsDAOList = new LinkedList<>();
        while (resultSet.next()) {
            DevelopersInProjectsDAO dao = new DevelopersInProjectsDAO();
            dao.setId(resultSet.getLong("id"));
            dao.setDeveloperId(resultSet.getLong("dev_id"));
            dao.setProjectId(resultSet.getLong("project_id"));
            developersInProjectsDAOList.add(dao);
        }
        return developersInProjectsDAOList;
    }
}
