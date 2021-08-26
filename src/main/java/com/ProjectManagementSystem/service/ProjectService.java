package com.ProjectManagementSystem.service;

import com.ProjectManagementSystem.dto.ProjectDTO;
import com.ProjectManagementSystem.model.dao.ProjectDAO;
import com.ProjectManagementSystem.model.repositories.EntityRepository;
import com.ProjectManagementSystem.model.repositories.ProjectRepository;
import com.ProjectManagementSystem.service.converter.Converter;
import com.ProjectManagementSystem.service.converter.ProjectConverter;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectService implements Service<ProjectDTO> {
    private final EntityRepository<ProjectDAO> repository;
    private final Converter<ProjectDAO, ProjectDTO> converter;

    public ProjectService() {
        this.repository = new ProjectRepository();
        this.converter = new ProjectConverter();
    }

    @Override
    public void create(ProjectDTO dto) {
        ProjectDAO dao = converter.fromDTO(dto);
        repository.create(dao);
    }

    @Override
    public void update(ProjectDTO dto) {
        ProjectDAO dao = converter.fromDTO(dto);
        repository.update(dao);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public Set<ProjectDTO> findAll() {
        Set<ProjectDAO> daoSet = repository.findAll();
        return daoSet.stream().map(converter::toDTO)
                .sorted(Comparator.comparing(ProjectDTO::getId))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public ProjectDTO findById(int id) {
        return converter.toDTO(repository.findById(id));
    }
}
