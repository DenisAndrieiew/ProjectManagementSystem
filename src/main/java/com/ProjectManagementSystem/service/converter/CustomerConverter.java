package com.ProjectManagementSystem.service.converter;

import com.ProjectManagementSystem.dto.CustomerDTO;
import com.ProjectManagementSystem.dto.ProjectDTO;
import com.ProjectManagementSystem.model.dao.CustomerDAO;
import com.ProjectManagementSystem.model.dao.ProjectDAO;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CustomerConverter implements Converter<CustomerDAO, CustomerDTO> {
    private static Converter<ProjectDAO, ProjectDTO> projectsConverter;

    public CustomerConverter() {
        projectsConverter = new ProjectConverter();
    }

    @Override
    public CustomerDAO fromDTO(CustomerDTO dto) {
        CustomerDAO dao = new CustomerDAO();
        dao.setName(dto.getName());
        dao.setId(dto.getId());
        Set<ProjectDAO> projects = new HashSet<>();
        if (Objects.nonNull(dto.getProjects())) {
            projects = projectsConverter.fromDTOSet(dto.getProjects());
        }
        dao.setProjects(projects);
        return dao;
    }

    @Override
    public CustomerDTO toDTO(CustomerDAO dao) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(dao.getId());
        dto.setName(dao.getName());
        dto.setProjects(projectsConverter.toDTOSet(dao.getProjects()));
        return dto;
    }
}
