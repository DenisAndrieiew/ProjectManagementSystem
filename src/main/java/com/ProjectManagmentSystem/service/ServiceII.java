package com.ProjectManagmentSystem.service;

import com.ProjectManagmentSystem.dao.Repository;
import com.ProjectManagmentSystem.dao.model.DataAccessObject;
import com.ProjectManagmentSystem.dao.model.DeveloperDAO;
import com.ProjectManagmentSystem.dto.DataTransferObject;
import com.ProjectManagmentSystem.dto.DeveloperDTO;
import com.ProjectManagmentSystem.service.Service;
import com.ProjectManagmentSystem.service.converter.Converter;

public class ServiceII implements Service<DataTransferObject> {
    private final Repository<DataTransferObject> repository;
    private final Converter<DataAccessObject, DataTransferObject> converter;

    public ServiceII(Repository<DataTransferObject> repository) {
        this.repository = repository;
        this.converter=repository.getConverter();
    }

    @Override
    public DataTransferObject create(DataTransferObject dataTransferObject) {
        return null;
    }

    @Override
    public DataTransferObject update(DataTransferObject dataTransferObject) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
