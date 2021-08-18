package com.ProjectManagementSystem.service;

import com.ProjectManagementSystem.dto.DataTransferObject;
import com.ProjectManagementSystem.model.dao.DataAccessObject;
import com.ProjectManagementSystem.model.repositories.Repository;
import com.ProjectManagementSystem.service.converter.Converter;

public class Service {
    private final Repository repository;
    private final Converter<DataAccessObject, DataTransferObject> converter;

    public Service(Repository repository) {
        this.repository = repository;
        this.converter = repository.getConverter();
    }

    public void create(DataTransferObject dto) {
        DataAccessObject dao = converter.fromDTO(dto);
        repository.create(dao);
        repository.findById(dao.getId());
    }

    public void update(DataTransferObject dto) {
        DataAccessObject dao = converter.fromDTO(dto);
        repository.update(dao);
        repository.findById(dao.getId());
    }

    public void delete(long id) {
        repository.delete(id);
    }

}
