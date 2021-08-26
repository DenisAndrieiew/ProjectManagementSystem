package com.ProjectManagementSystem.service;

import com.ProjectManagementSystem.dto.SkillsDTO;
import com.ProjectManagementSystem.model.dao.SkillsDAO;
import com.ProjectManagementSystem.model.repositories.EntityRepository;
import com.ProjectManagementSystem.model.repositories.SkillsRepository;
import com.ProjectManagementSystem.service.converter.Converter;
import com.ProjectManagementSystem.service.converter.SkillsConverter;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SkillsService implements Service<SkillsDTO> {
    private final EntityRepository<SkillsDAO> repository;
    private final Converter<SkillsDAO, SkillsDTO> converter;

    public SkillsService() {
        this.repository = new SkillsRepository();
        this.converter = new SkillsConverter();
    }

    @Override
    public void create(SkillsDTO dto) {
        SkillsDAO dao = converter.fromDTO(dto);
        repository.create(dao);
    }

    @Override
    public void update(SkillsDTO dto) {
        SkillsDAO dao = converter.fromDTO(dto);
        repository.update(dao);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public Set<SkillsDTO> findAll() {
        Set<SkillsDAO> daoSet = repository.findAll();
        return daoSet.stream().map(converter::toDTO)
                .sorted(Comparator.comparing(SkillsDTO::getId))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public SkillsDTO findById(int id) {
        return converter.toDTO(repository.findById(id));
    }
}
