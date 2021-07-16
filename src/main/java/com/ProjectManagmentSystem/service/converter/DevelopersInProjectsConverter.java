package com.ProjectManagmentSystem.service.converter;

import com.ProjectManagmentSystem.dao.model.DevelopersInProjectsDAO;
import com.ProjectManagmentSystem.dto.DevelopersInProjectsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DevelopersInProjectsConverter implements
        Converter<DevelopersInProjectsDAO, DevelopersInProjectsDTO> {

    public DevelopersInProjectsDAO fromDTO(DevelopersInProjectsDTO dto) {
        DevelopersInProjectsDAO dao = new DevelopersInProjectsDAO(dto.getDevId(), dto.getProjectId());
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
