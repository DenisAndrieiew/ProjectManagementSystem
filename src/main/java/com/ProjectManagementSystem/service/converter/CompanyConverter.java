package com.ProjectManagementSystem.service.converter;

import com.ProjectManagementSystem.dto.CompanyDTO;
import com.ProjectManagementSystem.dto.ProjectDTO;
import com.ProjectManagementSystem.model.dao.CompanyDAO;
import com.ProjectManagementSystem.model.dao.ProjectDAO;

public class CompanyConverter implements Converter<CompanyDAO, CompanyDTO> {
    private static Converter<ProjectDAO, ProjectDTO> projectsConverter;

    public CompanyConverter() {
        projectsConverter = new ProjectConverter();
    }

    @Override
    public CompanyDAO fromDTO(CompanyDTO dto) {
        CompanyDAO dao = new CompanyDAO();
        dao.setId(dto.getId());
        dao.setName(dto.getName());
        dao.setProjects(projectsConverter.fromDTOSet(dto.getProjects()));
        return dao;
    }

    @Override
    public CompanyDTO toDTO(CompanyDAO dao) {
        CompanyDTO dto = new CompanyDTO();
        dto.setId(dao.getId());
        dto.setName(dao.getName());
        dto.setProjects(projectsConverter.toDTOSet(dao.getProjects()));
        return dto;
    }
}
