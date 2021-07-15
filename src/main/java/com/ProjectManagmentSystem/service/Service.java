package com.ProjectManagmentSystem.service;

import com.ProjectManagmentSystem.dao.Repository;
import com.ProjectManagmentSystem.dao.model.DataAccessObject;
import com.ProjectManagmentSystem.dao.model.DeveloperDAO;
import com.ProjectManagmentSystem.dto.DataTransferObject;
import com.ProjectManagmentSystem.dto.DeveloperDTO;
import com.ProjectManagmentSystem.service.Service;
import com.ProjectManagmentSystem.service.converter.Converter;

public class Service {
    private final Repository<DataAccessObject> repository;
    private final Converter<DataAccessObject, DataTransferObject> converter;

    public Service(Repository<DataAccessObject> repository) {
        this.repository = repository;
        this.converter=repository.getConverter();
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
    private boolean  validate() {
        return DataAccessObject.getObjectName().equals(DataTransferObject.getObjectName());
    }
}
