package com.ProjectManagementSystem.service;

import com.ProjectManagementSystem.dto.DeveloperDTO;
import com.ProjectManagementSystem.model.dao.DeveloperDAO;
import com.ProjectManagementSystem.model.repositories.EntityRepository;
import com.ProjectManagementSystem.service.converter.Converter;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DeveloperService implements Service<DeveloperDTO> {
    private final EntityRepository<DeveloperDAO> repository;
    private final Converter<DeveloperDAO, DeveloperDTO> converter;

    public DeveloperService(EntityRepository repository) {
        this.repository = repository;
        this.converter = repository.getConverter();
    }

    @Override
    public void create(DeveloperDTO dto) {
        DeveloperDAO dao = converter.fromDTO(dto);
        repository.create(dao);
    }

    @Override
    public void update(DeveloperDTO dto) {
        DeveloperDAO dao = converter.fromDTO(dto);
        repository.update(dao);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public Set<DeveloperDTO> findAll() {
        Set<DeveloperDAO> daoSet = repository.findAll();
        Set<DeveloperDTO> dtoSet = daoSet.stream().map(dao -> converter.toDTO(dao))
                .sorted(Comparator.comparing(DeveloperDTO::getId))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return dtoSet;
    }

    @Override
    public DeveloperDTO findById(int id) {
        return converter.toDTO(repository.findById(id));
    }
}
