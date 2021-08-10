package com.ProjectManagementSystem.service.converter;

import com.ProjectManagementSystem.dao.*;
import com.ProjectManagementSystem.dao.model.*;
import com.ProjectManagementSystem.dto.DeveloperDTO;
import com.ProjectManagementSystem.dto.enums.Sex;
import com.ProjectManagementSystem.jdbc.config.DatabaseConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DeveloperConverter implements Converter<DeveloperDAO, DeveloperDTO> {

    public DeveloperDAO fromDTO(DeveloperDTO dto) {
        DeveloperDAO dao = new DeveloperDAO(dto.getFirstName(), dto.getLastName(),
                dto.getAge(), dto.getSex(), dto.getComments(), dto.getSalary());
        if (dto.getId() != 0) dao.setId(dto.getId());
        return dao;
    }

    public DeveloperDTO toDTO(DeveloperDAO dao) {
        DeveloperDTO dto = new DeveloperDTO(dao.getId(), dao.getFirstName(), dao.getLastName(),
                dao.getAge(), dao.getSex(), dao.getComments(), dao.getSalary());
        Repository<DevelopersInProjectsDAO> devInProjects = new DevelopersInProjectsRepository();
        Repository<ProjectsDAO> projectsDao = new ProjectsRepository();
        List<DevelopersInProjectsDAO> projects = devInProjects.findByNumber("dev_id", dto.getId());
        List<String> devProjects = projects.stream()
                .map((devInProject) -> projectsDao.findById(devInProject.getProjectId()).getName())
                .collect(Collectors.toList());
        dto.setProjects(devProjects);
        Repository<BrunchDAO> brunches = new BrunchRepository();
        Repository<SkillLevelDAO>skills = new SkillLevelRepository();
        Repository<DevSkillsDAO> devSkills = new DevSkillsRepository();
        Map<String, String> skillLevels = new HashMap<String, String>();
        List<DevSkillsDAO> devSkill =devSkills.findByNumber("dev_id", dto.getId());
        for (DevSkillsDAO skill:devSkill) {
            skillLevels.put(brunches.findById(skill.getSkillId()).getBrunch(),
                    skills.findById(skill.getSkillLevel()).getName());
        }
        dto.setSkillLevels(skillLevels);
        return dto;
    }


    public List<DeveloperDAO> fromResultSet(ResultSet resultSet) throws SQLException {
        List<DeveloperDAO> developers = new LinkedList<>();
        while (resultSet.next()) {
            DeveloperDAO dao = new DeveloperDAO();
            dao.setId(resultSet.getLong("id"));
            dao.setFirstName(resultSet.getString("first_name"));
            dao.setLastName(resultSet.getString("last_name"));
            dao.setAge(resultSet.getInt("age"));
            dao.setSex(Sex.valueOf(resultSet.getString("dev_sex").toUpperCase()));
            dao.setComments(resultSet.getString("comments"));
            dao.setSalary(resultSet.getInt("salary"));
            developers.add(dao);
        }
        return developers;
    }

}
