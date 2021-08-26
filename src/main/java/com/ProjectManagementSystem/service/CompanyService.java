package com.ProjectManagementSystem.service;

import com.ProjectManagementSystem.dto.CompanyDTO;
import com.ProjectManagementSystem.model.dao.CompanyDAO;
import com.ProjectManagementSystem.model.repositories.CompanyRepository;
import com.ProjectManagementSystem.model.repositories.EntityRepository;
import com.ProjectManagementSystem.service.converter.CompanyConverter;
import com.ProjectManagementSystem.service.converter.Converter;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CompanyService implements Service<CompanyDTO> {
    private final EntityRepository<CompanyDAO> repository;
    private final Converter<CompanyDAO, CompanyDTO> converter;

    public CompanyService() {
        this.repository = new CompanyRepository();
        this.converter = new CompanyConverter();
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
        return daoSet.stream().map(converter::toDTO)
                .sorted(Comparator.comparing(CompanyDTO::getId))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public CompanyDTO findById(int id) {
        return converter.toDTO(repository.findById(id));
    }
}
