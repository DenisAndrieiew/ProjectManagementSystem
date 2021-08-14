package com.ProjectManagementSystem.service.converter;

import com.ProjectManagementSystem.repository.*;
import com.ProjectManagementSystem.repository.model.*;
import com.ProjectManagementSystem.dto.DeveloperDTO;
import com.ProjectManagementSystem.dto.enums.Sex;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DeveloperConverter implements Converter<DeveloperDAO, DeveloperDTO> {

    public DeveloperDAO fromDTO(DeveloperDTO dto) {
        ProjectsRepository projectsRepository = new ProjectsRepository();
        DeveloperDAO dao = new DeveloperDAO(dto.getFirstName(), dto.getLastName(),
                dto.getAge(), dto.getSex(), dto.getComments(), dto.getSalary());
        if (dto.getId() != 0) dao.setId(dto.getId());
        List<ProjectsDAO> projectsDAO = new LinkedList<>();
        for (String project : dto.getProjects()) {
            projectsDAO.add(projectsRepository.findByString("name", project).get(0));
        }
        dao.setProjects(projectsDAO);
        return dao;
    }

    public DeveloperDTO toDTO(DeveloperDAO dao) {
        DeveloperDTO dto = new DeveloperDTO(dao.getId(), dao.getFirstName(), dao.getLastName(),
                dao.getAge(), dao.getSex(), dao.getComments(), dao.getSalary());
        List<String> projects = new LinkedList<>();
        dao.getProjects().forEach(p -> projects.add(p.getName()));
        dto.setProjects(projects);
        Repository<BrunchDAO> brunches = new BrunchRepository();
        Repository<SkillLevelDAO> skills = new SkillLevelRepository();
        Repository<DevSkillsDAO> devSkills = new DevSkillsRepository();
        Map<String, String> skillLevels = new HashMap<String, String>();
        List<DevSkillsDAO> devSkill = devSkills.findByNumber("dev_id", dto.getId());
        for (DevSkillsDAO skill : devSkill) {
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

            ProjectsRepository projectsRepository = new ProjectsRepository();
            DevelopersInProjectsRepository dipRepository = new DevelopersInProjectsRepository();
            List<ProjectsDAO> projectsDAOS = dipRepository.findByNumber("dev_id", dao.getId()).stream()
                    .map(dip -> projectsRepository.findById(dip.getProjectId())).collect(Collectors.toList());
            dao.setProjects(projectsDAOS);
            developers.add(dao);
        }
        return developers;
    }

}
