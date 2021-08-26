package com.ProjectManagementSystem.service.converter;

import com.ProjectManagementSystem.dto.SkillsDTO;
import com.ProjectManagementSystem.model.dao.SkillsDAO;
import com.ProjectManagementSystem.model.repositories.SkillsRepository;
import com.ProjectManagementSystem.model.repositories.SkillsRepositoryInterface;

public class SkillsConverter implements Converter<SkillsDAO, SkillsDTO> {
    private static SkillsRepositoryInterface skillsRepository;

    public SkillsConverter() {
        skillsRepository = new SkillsRepository();
    }

    @Override
    public SkillsDAO fromDTO(SkillsDTO dto) {
        SkillsDAO dao = skillsRepository.findByBrunchAndLevel(dto.getBrunch(), dto.getLevel());
        return dao;
    }

    @Override
    public SkillsDTO toDTO(SkillsDAO dao) {
        SkillsDTO dto = new SkillsDTO();
        dto.setId(dao.getId());
        dto.setLevel(dao.getLevel().toString());
        dto.setBrunch(dao.getBrunch().toString());
        return dto;
    }
}
