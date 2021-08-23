package com.ProjectManagementSystem.service;

import com.ProjectManagementSystem.dto.ProjectDTO;
import com.ProjectManagementSystem.model.dao.ProjectDAO;
import com.ProjectManagementSystem.model.repositories.EntityRepository;
import com.ProjectManagementSystem.service.converter.Converter;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectService implements Service<ProjectDTO> {
    private final EntityRepository<ProjectDAO> repository;
    private final Converter<ProjectDAO, ProjectDTO> converter;

    public ProjectService(EntityRepository repository) {
        this.repository = repository;
        this.converter = repository.getConverter();
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
        Set<ProjectDTO> dtoSet = daoSet.stream().map(dao -> converter.toDTO(dao))
                .sorted(Comparator.comparing(ProjectDTO::getId))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return dtoSet;
    }

    @Override
    public ProjectDTO findById(int id) {
        return converter.toDTO(repository.findById(id));
    }
}
