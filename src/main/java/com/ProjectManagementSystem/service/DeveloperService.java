package com.ProjectManagementSystem.service;

import com.ProjectManagementSystem.dto.DeveloperDTO;
import com.ProjectManagementSystem.model.dao.DeveloperDAO;
import com.ProjectManagementSystem.model.repositories.DeveloperRepository;
import com.ProjectManagementSystem.model.repositories.EntityRepository;
import com.ProjectManagementSystem.service.converter.Converter;
import com.ProjectManagementSystem.service.converter.DeveloperConverter;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DeveloperService implements Service<DeveloperDTO> {
    private final EntityRepository<DeveloperDAO> repository;
    private final Converter<DeveloperDAO, DeveloperDTO> converter;

    public DeveloperService() {
        this.repository = new DeveloperRepository();
        this.converter = new DeveloperConverter();
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
        return daoSet.stream().map(converter::toDTO)
                .sorted(Comparator.comparing(DeveloperDTO::getId))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public DeveloperDTO findById(int id) {
        return converter.toDTO(repository.findById(id));
    }
}
