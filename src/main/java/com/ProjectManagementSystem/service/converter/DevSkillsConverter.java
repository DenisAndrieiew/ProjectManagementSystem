package com.ProjectManagementSystem.service.converter;

import com.ProjectManagementSystem.dto.DevSkillsDTO;
import com.ProjectManagementSystem.dto.enums.Brunch;
import com.ProjectManagementSystem.dto.enums.SkillLevel;
import com.ProjectManagementSystem.model.dao.BrunchDAO;
import com.ProjectManagementSystem.model.dao.DevSkillsDAO;
import com.ProjectManagementSystem.model.dao.DeveloperDAO;
import com.ProjectManagementSystem.model.dao.SkillLevelDAO;
import com.ProjectManagementSystem.model.repositories.EntityRepository;
import com.ProjectManagementSystem.model.repositories.GenericEntityRepository;

public class DevSkillsConverter implements Converter<DevSkillsDAO, DevSkillsDTO> {
    private static EntityRepository<BrunchDAO> brunchRepository;
    private static EntityRepository<SkillLevelDAO> levelRepository;
    private static EntityRepository<DeveloperDAO> developerRepository;

    public DevSkillsConverter() {
        brunchRepository = new GenericEntityRepository<>(BrunchDAO.class);
        levelRepository = new GenericEntityRepository<>(SkillLevelDAO.class);
        developerRepository = new GenericEntityRepository<>(DeveloperDAO.class);
    }

    @Override
    public DevSkillsDAO fromDTO(DevSkillsDTO dto) {
        DevSkillsDAO dao = new DevSkillsDAO();
        dao.setId(dto.getId());
        dao.setBrunch(brunchRepository.findEnum("brunch", Brunch.toBrunch(dto.getBrunch()).get()));
        dao.setSkillLevel(levelRepository.findEnum("level", SkillLevel.toSkillLevel(dto.getLevel()).get()));
        dao.setDeveloper(developerRepository.findById(dto.getDeveloperId()));
        return dao;
    }

    @Override
    public DevSkillsDTO toDTO(DevSkillsDAO dao) {
        DevSkillsDTO dto = new DevSkillsDTO();
        dto.setId(dao.getId());
        dto.setDeveloperId(dao.getDeveloper().getId());
        dto.setLevel(dao.getSkillLevel().getLevel().toString());
        dto.setBrunch(dao.getBrunch().getBrunch().toString());
        return dto;
    }
}
