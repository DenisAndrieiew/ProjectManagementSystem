package com.ProjectManagmentSystem.service;

import com.ProjectManagmentSystem.dao.Repository;
import com.ProjectManagmentSystem.dao.model.DeveloperDAO;
import com.ProjectManagmentSystem.dto.DeveloperDTO;
import com.ProjectManagmentSystem.service.converter.Converter;

public class DeveloperService implements Service<DeveloperDTO>{
    private final Repository<DeveloperDAO> repository;
    private final Converter<DeveloperDAO, DeveloperDTO> converter;

    public DeveloperService(Repository<DeveloperDAO> repository) {

        this.repository = repository;
        this.converter=repository.getConverter();
    }

    public DeveloperDTO create(DeveloperDTO developerDTO) {
        DeveloperDAO developerDAO = converter.fromDTO(developerDTO);
        repository.create(developerDAO);
        DeveloperDAO savedDevDAO = repository.findById(developerDAO.getId());
        return converter.toDTO(savedDevDAO);
    }

    public DeveloperDTO update(DeveloperDTO dto) {
        DeveloperDAO dao = converter.fromDTO(dto);
        repository.update(dao);
        DeveloperDAO updatedDAO = repository.findById(dto.getId());
        return converter.toDTO(updatedDAO);
    }

    public void delete(long id) {
        repository.delete(id);
    }
}
