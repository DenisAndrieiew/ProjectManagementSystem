package com.ProjectManagmentSystem.service;

import com.ProjectManagmentSystem.dao.Repository;
import com.ProjectManagmentSystem.dao.model.DeveloperDAO;
import com.ProjectManagmentSystem.dto.DeveloperDTO;

public class DeveloperService {
    private final Repository<DeveloperDAO> repository;

    public DeveloperService(Repository<DeveloperDAO> repository) {
        this.repository = repository;
    }

    public DeveloperDTO create(DeveloperDTO developerDTO) {
        DeveloperDAO developerDAO = DeveloperConverter.toDeveloper(developerDTO);
        repository.create(developerDAO);
        DeveloperDAO savedDevDAO = repository.findById(developerDAO.getId());
        return DeveloperConverter.fromDeveloper(savedDevDAO);
    }

    public DeveloperDTO update(DeveloperDTO dto) {
        DeveloperDAO dao = DeveloperConverter.toDeveloper(dto);
        repository.update(dao);
        DeveloperDAO updatedDAO = repository.findById(dto.getId());
        return DeveloperConverter.fromDeveloper(updatedDAO);
    }

    public void delete(long id) {
        repository.delete(id);
    }
}
