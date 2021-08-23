package com.ProjectManagementSystem.service;

import com.ProjectManagementSystem.dto.DevSkillsDTO;
import com.ProjectManagementSystem.model.dao.DevSkillsDAO;
import com.ProjectManagementSystem.model.repositories.EntityRepository;
import com.ProjectManagementSystem.service.converter.Converter;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DevSkillsService implements Service<DevSkillsDTO> {
    private final EntityRepository<DevSkillsDAO> repository;
    private final Converter<DevSkillsDAO, DevSkillsDTO> converter;

    public DevSkillsService(EntityRepository repository) {
        this.repository = repository;
        this.converter = repository.getConverter();
    }

    @Override
    public void create(DevSkillsDTO dto) {
        DevSkillsDAO dao = converter.fromDTO(dto);
        repository.create(dao);
    }

    @Override
    public void update(DevSkillsDTO dto) {
        DevSkillsDAO dao = converter.fromDTO(dto);
        repository.update(dao);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public Set<DevSkillsDTO> findAll() {
        Set<DevSkillsDAO> daoSet = repository.findAll();
        Set<DevSkillsDTO> dtoSet = daoSet.stream().map(dao -> converter.toDTO(dao))
                .sorted(Comparator.comparing(DevSkillsDTO::getId))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return dtoSet;
    }

    @Override
    public DevSkillsDTO findById(int id) {
        return converter.toDTO(repository.findById(id));
    }
}
