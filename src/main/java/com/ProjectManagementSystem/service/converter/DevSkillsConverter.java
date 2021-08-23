package com.ProjectManagementSystem.service.converter;

import com.ProjectManagementSystem.dto.DevSkillsDTO;
import com.ProjectManagementSystem.model.dao.DevSkillsDAO;
import com.ProjectManagementSystem.model.repositories.DevSkillsRepository;
import com.ProjectManagementSystem.model.repositories.SkillsRepository;

public class DevSkillsConverter implements Converter<DevSkillsDAO, DevSkillsDTO> {
    private static SkillsRepository skillsRepository;

    public DevSkillsConverter() {
        skillsRepository = new DevSkillsRepository();
    }

    @Override
    public DevSkillsDAO fromDTO(DevSkillsDTO dto) {
        DevSkillsDAO dao = skillsRepository.findByBrunchAndLevel(dto.getBrunch(), dto.getLevel());
        return dao;
    }

    @Override
    public DevSkillsDTO toDTO(DevSkillsDAO dao) {
        DevSkillsDTO dto = new DevSkillsDTO();
        dto.setId(dao.getId());
        dto.setLevel(dao.getLevel().toString());
        dto.setBrunch(dao.getBrunch().toString());
        return dto;
    }
}
