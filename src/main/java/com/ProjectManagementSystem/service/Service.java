package com.ProjectManagementSystem.service;

import com.ProjectManagementSystem.dto.CompanyDTO;
import com.ProjectManagementSystem.dto.DataTransferObject;
import com.ProjectManagementSystem.model.dao.DataAccessObject;
import com.ProjectManagementSystem.model.repositories.Repository;
import com.ProjectManagementSystem.service.converter.Converter;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public interface Service<T> {
public void create(T dto);
public void update(T dto);
public void delete(int id);
public Set<T> findAll();
public T findById(int id);

//    private final EntityRe repository;
//    private final Converter<DataAccessObject, DataTransferObject> converter;
//
//    public Service(Repository repository) {
//        this.repository = repository;
//        this.converter = repository.getConverter();
//    }
//
//    public int create(T dto) {
//        DataAccessObject dao = converter.fromDTO(dto);
//        repository.create(dao);
//        return dao.getId();
//    }
//
//    public void update(T dto) {
//        DataAccessObject dao = converter.fromDTO(dto);
//        repository.update(dao);
//        repository.findById(dao.getId());
//    }
//
//    public void delete(int id) {
//        repository.delete(id);
//    }
//    public Set<T> findAll() {
//        Set<T> dtoSet = repository.findAll().stream()
//                .map(dao -> (CompanyDTO) converter.toDTO(dao))
//                .sorted(Comparator.comparing(CompanyDTO::getId))
//                .collect(Collectors.toCollection(LinkedHashSet::new));
//
//    }

}
