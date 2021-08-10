package com.ProjectManagementSystem.service;

import com.ProjectManagementSystem.dao.Repository;
import com.ProjectManagementSystem.dao.model.DataAccessObject;
import com.ProjectManagementSystem.dto.DataTransferObject;
import com.ProjectManagementSystem.service.converter.Converter;

public class Service {
    private final Repository repository;
    private final Converter<DataAccessObject, DataTransferObject> converter;

    public Service(Repository repository) {
        this.repository = repository;
        this.converter = repository.getConverter();
    }

    public DataTransferObject create(DataTransferObject dto) {
        DataAccessObject dao = converter.fromDTO(dto);
        repository.create(dao);
        DataAccessObject savedDAO = repository.findById(dao.getId());
        return converter.toDTO(savedDAO);
    }

    public DataTransferObject update(DataTransferObject dto) {
        DataAccessObject dao = converter.fromDTO(dto);
        repository.update(dao);
        DataAccessObject updatedDAO = repository.findById(dao.getId());
        return converter.toDTO(updatedDAO);
    }

    public void delete(long id) {
        repository.delete(id);
    }

}
