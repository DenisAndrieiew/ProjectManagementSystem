package com.ProjectManagementSystem.service.converter;

import com.ProjectManagementSystem.dto.DeveloperDTO;
import com.ProjectManagementSystem.dto.enums.Brunch;
import com.ProjectManagementSystem.dto.enums.Sex;
import com.ProjectManagementSystem.dto.enums.SkillLevel;
import com.ProjectManagementSystem.repository.*;
import com.ProjectManagementSystem.repository.model.BrunchDAO;
import com.ProjectManagementSystem.repository.model.DeveloperDAO;
import com.ProjectManagementSystem.repository.model.ProjectsDAO;
import com.ProjectManagementSystem.repository.model.SkillLevelDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
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
        if (!dto.getSkillLevels().isEmpty()) {
            Map<Long, Long> skillLevelsMap = new HashMap<>();
            List<BrunchDAO> brunches = new BrunchRepository().findAll();
            List<SkillLevelDAO> skillLevels = new SkillLevelRepository().findAll();
            dto.getSkillLevels().forEach((skill, level) -> {
                Iterator<BrunchDAO> brIterator = brunches.iterator();
                long skill_id = brunches.stream()
                        .filter(brunch -> brunch.getBrunch()
                                .equalsIgnoreCase(Brunch.valueOf(skill).toString()))
                        .findFirst().get().getId();
                long level_id = skillLevels.stream()
                        .filter(skillLevel -> skillLevel.getName()
                                .equalsIgnoreCase(SkillLevel.valueOf(level.toUpperCase(Locale.ROOT))
                                        .toString())).findFirst().get().getId();
                skillLevelsMap.put(skill_id, level_id);
            });
            dao.setSkillLevels(skillLevelsMap);
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
        Map<String, String> skillLevels = new HashMap<>();
        dao.getSkillLevels().forEach((skill, level) ->
                skillLevels.put(brunches.findById(skill).getBrunch(), skills.findById(level).getName()));
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
            DevSkillsRepository skillLevelRepository = new DevSkillsRepository();
            Map<Long, Long> skillLevels = new HashMap<>();
            skillLevelRepository.findByNumber("dev_id", dao.getId())
                    .stream().forEach(entity -> skillLevels.put(entity.getSkillId(), entity.getSkillLevel()));
            dao.setSkillLevels(skillLevels);
            developers.add(dao);
        }
        return developers;
    }

}
