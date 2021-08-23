package com.ProjectManagementSystem.service;

import com.ProjectManagementSystem.dto.CompanyDTO;
import com.ProjectManagementSystem.model.dao.CompanyDAO;
import com.ProjectManagementSystem.model.repositories.EntityRepository;
import com.ProjectManagementSystem.service.converter.Converter;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CompanyService implements Service<CompanyDTO> {
    private final EntityRepository<CompanyDAO> repository;
    private final Converter<CompanyDAO, CompanyDTO> converter;

    public CompanyService(EntityRepository repository) {
        this.repository = repository;
        this.converter = repository.getConverter();
    }

    @Override
    public void create(CompanyDTO dto) {
        CompanyDAO dao = converter.fromDTO(dto);
        repository.create(dao);
    }

    @Override
    public void update(CompanyDTO dto) {
        CompanyDAO dao = converter.fromDTO(dto);
        repository.update(dao);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public Set<CompanyDTO> findAll() {
        Set<CompanyDAO> daoSet = repository.findAll();
        Set<CompanyDTO> dtoSet = daoSet.stream().map(dao -> converter.toDTO(dao))
                .sorted(Comparator.comparing(CompanyDTO::getId))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return dtoSet;
    }

    @Override
    public CompanyDTO findById(int id) {
        return converter.toDTO(repository.findById(id));
    }
}
