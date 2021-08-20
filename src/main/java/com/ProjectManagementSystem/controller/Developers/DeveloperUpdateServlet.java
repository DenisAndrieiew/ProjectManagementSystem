package com.ProjectManagementSystem.controller.Developers;

import com.ProjectManagementSystem.dto.DevSkillsDTO;
import com.ProjectManagementSystem.dto.DeveloperDTO;
import com.ProjectManagementSystem.dto.ProjectDTO;
import com.ProjectManagementSystem.dto.enums.Brunch;
import com.ProjectManagementSystem.dto.enums.Sex;
import com.ProjectManagementSystem.dto.enums.SkillLevel;
import com.ProjectManagementSystem.model.dao.BrunchDAO;
import com.ProjectManagementSystem.model.dao.DeveloperDAO;
import com.ProjectManagementSystem.model.dao.ProjectDAO;
import com.ProjectManagementSystem.model.repositories.EntityRepository;
import com.ProjectManagementSystem.model.repositories.GenericEntityRepository;
import com.ProjectManagementSystem.service.Service;
import com.ProjectManagementSystem.service.converter.Converter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/developers/update")
public class DeveloperUpdateServlet extends HttpServlet {
    private EntityRepository<ProjectDAO> projectRepository;
    private Converter projectConverter;
    private EntityRepository<BrunchDAO> brunchRepository;
    private Converter brunchConverter;
    private Service developerService;
    private EntityRepository<DeveloperDAO> developerRepository;
    private Converter<DeveloperDAO, DeveloperDTO> developerConverter;

    @Override
    public void init() throws ServletException {
        this.projectRepository = new GenericEntityRepository<>(ProjectDAO.class);
        this.projectConverter = projectRepository.getConverter();
        this.brunchRepository = new GenericEntityRepository<>(BrunchDAO.class);
        this.brunchConverter = brunchRepository.getConverter();
        this.developerRepository=new GenericEntityRepository<>(DeveloperDAO.class);
        this.developerService=new Service(developerRepository);
        this.developerConverter=developerRepository.getConverter();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProjectDTO> projects = projectRepository.findAll().stream()
                .map(dao -> (ProjectDTO) projectConverter.toDTO(dao))
                .collect(Collectors.toList());
        List<String> brunches = Arrays.stream(Brunch.values()).map(Brunch::toString).collect(Collectors.toList());
        List<String> skillLevels = (Arrays.stream(SkillLevel.values()).map(SkillLevel::toString))
                .collect(Collectors.toList());
        req.setAttribute("projects", projects);
        req.setAttribute("brunches", brunches);
        req.setAttribute("skillLevels", skillLevels);
        req.setAttribute("method", "post");
        int id = Integer.parseInt(req.getParameter("id"));
        DeveloperDTO developer = developerConverter.toDTO(developerRepository.findById(id));
        req.setAttribute("developer", developer);
        req.getRequestDispatcher("/view/developerUpdate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DeveloperDTO developerDTO = new DeveloperDTO();
        developerDTO.setId(Integer.parseInt(req.getParameter("id")));
        developerDTO.setFirstName(req.getParameter("firstName"));
        developerDTO.setLastName(req.getParameter("lastName"));
        developerDTO.setAge(Integer.parseInt(req.getParameter("age")));
        developerDTO.setSex(Sex.valueOf(req.getParameter("sex")));
        developerDTO.setSalary(Integer.parseInt(req.getParameter("salary")));
        developerDTO.setComments(req.getParameter("comments"));
        String[] projectsFromForm = req.getParameterValues("in_project");
        if (Objects.nonNull(projectsFromForm)) {
            Set<ProjectDTO> projects = Arrays.asList(projectsFromForm).stream()
                    .map(Integer::parseInt)
                    .map(projectRepository::findById)
                    .map(dao->(ProjectDTO) projectConverter.toDTO(dao))
                    .collect(Collectors.toSet());
            developerDTO.setProjects(projects);
        }
        List<String> brunches = Arrays.stream(Brunch.values()).map(Brunch::toString).collect(Collectors.toList());
        List<String> levels = Arrays.asList(req.getParameterValues("skill_level").clone());
        Iterator<String> brIterator = brunches.iterator();
        Iterator<String> lvlIterator = levels.iterator();
        Set<DevSkillsDTO> devSkills = new HashSet<>();
        while (brIterator.hasNext() && lvlIterator.hasNext()) {
            DevSkillsDTO ds = new DevSkillsDTO();
            ds.setBrunch(brIterator.next());
            ds.setLevel(lvlIterator.next());
            ds.setDeveloperId(developerDTO.getId());
            devSkills.add(ds);
        }
        devSkills.removeIf(devSkill->devSkill.getLevel().equalsIgnoreCase("none"));
        devSkills.forEach(devSkill->devSkill.setDeveloperId(developerDTO.getId()));
        developerDTO.setDevSkills(devSkills);
        developerService.update(developerDTO);
        resp.sendRedirect(req.getContextPath() + "/developers");
    }
}
