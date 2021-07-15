package com.ProjectManagmentSystem.service;

import com.ProjectManagmentSystem.dao.Repository;
import com.ProjectManagmentSystem.dao.model.DevSkillsDAO;
import com.ProjectManagmentSystem.dao.model.DeveloperDAO;
import com.ProjectManagmentSystem.dto.DevSkillsDTO;
import com.ProjectManagmentSystem.dto.DeveloperDTO;
import com.ProjectManagmentSystem.service.converter.Converter;

public class DevSkillsService implements Service<DevSkillsDTO> {

    private final Repository<DevSkillsDAO> repository;
    private final Converter<DevSkillsDAO, DevSkillsDTO> converter;

    public DevSkillsService(Repository<DevSkillsDAO> repository) {
        this.repository = repository;
        this.converter= repository.getConverter();
    }

    public DevSkillsDTO create(DevSkillsDTO dto){
        DevSkillsDAO dao = converter.fromDTO(dto);
        repository.create(dao);
        DevSkillsDAO savedDAO = repository.findById(dto.getId());
        return converter.toDTO()
    }

    @Override
    public DevSkillsDTO update(DevSkillsDTO dto) {
        return null;
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
