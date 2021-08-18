package com.ProjectManagementSystem.service.converter;

import com.ProjectManagementSystem.dto.CustomerDTO;
import com.ProjectManagementSystem.dto.ProjectDTO;
import com.ProjectManagementSystem.model.dao.CustomerDAO;
import com.ProjectManagementSystem.model.dao.ProjectDAO;

public class CustomerConverter implements Converter<CustomerDAO, CustomerDTO> {
    private static Converter<ProjectDAO, ProjectDTO> projectsConverter;

    public CustomerConverter() {
        projectsConverter = new ProjectConverter();
    }

    @Override
    public CustomerDAO fromDTO(CustomerDTO dto) {
        CustomerDAO dao = new CustomerDAO();
        dao.setId(dto.getId());
        dao.setName(dto.getName());
        dao.setProjects(projectsConverter.fromDTOSet(dto.getProjects()));
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
