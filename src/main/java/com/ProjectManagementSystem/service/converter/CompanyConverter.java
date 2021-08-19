package com.ProjectManagementSystem.service.converter;

import com.ProjectManagementSystem.dto.CompanyDTO;
import com.ProjectManagementSystem.dto.ProjectDTO;
import com.ProjectManagementSystem.model.dao.CompanyDAO;
import com.ProjectManagementSystem.model.dao.ProjectDAO;
import com.ProjectManagementSystem.model.repositories.EntityRepository;
import com.ProjectManagementSystem.model.repositories.GenericEntityRepository;

import java.util.HashSet;
import java.util.Set;

public class CompanyConverter implements Converter<CompanyDAO, CompanyDTO> {
    private static Converter<ProjectDAO, ProjectDTO> projectsConverter;
    private static EntityRepository<ProjectDAO> projectRepository;
    public CompanyConverter() {
        projectsConverter = new ProjectConverter();
        projectRepository=new GenericEntityRepository<>(ProjectDAO.class);
    }

    @Override
    public CompanyDAO fromDTO(CompanyDTO dto) {
        CompanyDAO dao = new CompanyDAO();
        dao.setId(dto.getId());
        dao.setName(dto.getName());
        Set<ProjectDAO> projects = new HashSet<>();
        dto.getProjectIds().forEach(id->projects.add(projectRepository.findById(id)));
        dao.setProjects(projects);
        return dao;
    }

    @Override
    public CompanyDTO toDTO(CompanyDAO dao) {
        CompanyDTO dto = new CompanyDTO();
        dto.setId(dao.getId());
        dto.setName(dao.getName());
        Set<Integer> projectIds = new HashSet<>();
        Set<String> projects = new HashSet<>();
        dao.getProjects().forEach(project->{
            projectIds.add(project.getId());
            projects.add(project.getName());
        });
        dto.setProjects(projects);
        dto.setProjectIds(projectIds);
        return dto;
    }
}
