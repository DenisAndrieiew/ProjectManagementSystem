package com.ProjectManagementSystem.service.converter;

import com.ProjectManagementSystem.repository.ProjectsRepository;
import com.ProjectManagementSystem.repository.model.CompanyDAO;
import com.ProjectManagementSystem.dto.CompanyDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CompanyConverter implements Converter<CompanyDAO, CompanyDTO> {
    @Override
    public CompanyDAO fromDTO(CompanyDTO dto) {
        CompanyDAO dao = new CompanyDAO();
        if (dto.getId() != 0) dao.setId(dto.getId());
        dao.setName(dto.getName());
        dao.setProjects(dto.getProjects());
        return dao;
    }

    @Override
    public CompanyDTO toDTO(CompanyDAO dao) {
        CompanyDTO dto = new CompanyDTO();
        dto.setProjects(dao.getProjects());
        dto.setId(dao.getId());
        dto.setName(dao.getName());
        return dto;
    }

    @Override
    public List<CompanyDAO> fromResultSet(ResultSet resultSet) throws SQLException {
        List<CompanyDAO> companies = new LinkedList<>();
        while (resultSet.next()) {
            CompanyDAO dao = new CompanyDAO();
            dao.setId(resultSet.getLong("id"));
            dao.setName(resultSet.getString("name"));
            Map<Long, String> projects = new HashMap<>();
            new ProjectsRepository().findByNumber("company_id", dao.getId()).stream()
                    .forEach(projectsDAO -> projects.put(projectsDAO.getId(), projectsDAO.getName()));
            dao.setProjects(projects);
            companies.add(dao);
        }
        return companies;
    }
}
