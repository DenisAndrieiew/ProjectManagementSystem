package com.ProjectManagementSystem.service.converter;

import com.ProjectManagementSystem.dto.DeveloperDTO;
import com.ProjectManagementSystem.model.dao.DeveloperDAO;

public class DeveloperConverter implements Converter<DeveloperDAO, DeveloperDTO> {
    private static ProjectConverter projectConverter = new ProjectConverter();
    private static DevSkillsConverter devSkillsConverter = new DevSkillsConverter();

    public DeveloperDAO fromDTO(DeveloperDTO dto) {
        DeveloperDAO dao = new DeveloperDAO();
        dao.setId(dto.getId());
        dao.setFirstName(dto.getFirstName());
        dao.setLastName(dto.getLastName());
        dao.setComments(dto.getComments());
        dao.setAge(dto.getAge());
        dao.setSalary(dto.getSalary());
        dao.setProjects(projectConverter.fromDTOSet(dto.getProjects()));
        dao.setDevSkills(devSkillsConverter.fromDTOSet(dto.getDevSkills()));
        dao.setSex(dto.getSex());
        return dao;
    }

    public DeveloperDTO toDTO(DeveloperDAO dao) {
        DeveloperDTO dto = new DeveloperDTO();
        dto.setId(dao.getId());
        dto.setFirstName(dao.getFirstName());
        dto.setLastName(dao.getLastName());
        dto.setComments(dao.getComments());
        dto.setSex(dao.getSex());
        dto.setAge(dao.getAge());
        dto.setSalary(dao.getSalary());
        dto.setProjects(projectConverter.toDTOSet(dao.getProjects()));
        dto.setDevSkills(devSkillsConverter.toDTOSet(dao.getDevSkills()));
        dto.setSex(dao.getSex());
        return dto;
    }
}
