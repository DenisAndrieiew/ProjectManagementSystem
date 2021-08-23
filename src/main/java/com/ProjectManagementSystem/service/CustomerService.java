package com.ProjectManagementSystem.service;

import com.ProjectManagementSystem.dto.CustomerDTO;
import com.ProjectManagementSystem.model.dao.CustomerDAO;
import com.ProjectManagementSystem.model.repositories.EntityRepository;
import com.ProjectManagementSystem.service.Service;
import com.ProjectManagementSystem.service.converter.Converter;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomerService implements Service<CustomerDTO> {
    private final EntityRepository<CustomerDAO> repository;
    private final Converter<CustomerDAO, CustomerDTO> converter;

    public CustomerService(EntityRepository repository) {
        this.repository = repository;
        this.converter = repository.getConverter();
    }

    @Override
    public void create(CustomerDTO dto) {
        CustomerDAO dao = converter.fromDTO(dto);
        repository.create(dao);
    }

    @Override
    public void update(CustomerDTO dto) {
        CustomerDAO dao = converter.fromDTO(dto);
        repository.update(dao);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public Set<CustomerDTO> findAll() {
        Set<CustomerDAO> daoSet = repository.findAll();
        Set<CustomerDTO> dtoSet = daoSet.stream().map(dao -> converter.toDTO(dao))
                .sorted(Comparator.comparing(CustomerDTO::getId))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return dtoSet;
    }

    @Override
    public CustomerDTO findById(int id) {
        return converter.toDTO(repository.findById(id));
    }
}
