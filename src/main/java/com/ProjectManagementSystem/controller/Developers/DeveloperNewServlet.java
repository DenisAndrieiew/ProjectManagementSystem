package com.ProjectManagementSystem.controller.Developers;

import com.ProjectManagementSystem.dto.SkillsDTO;
import com.ProjectManagementSystem.dto.DeveloperDTO;
import com.ProjectManagementSystem.dto.ProjectDTO;
import com.ProjectManagementSystem.dto.enums.Brunch;
import com.ProjectManagementSystem.dto.enums.Sex;
import com.ProjectManagementSystem.dto.enums.SkillLevel;
import com.ProjectManagementSystem.model.dao.DeveloperDAO;
import com.ProjectManagementSystem.model.dao.ProjectDAO;
import com.ProjectManagementSystem.model.repositories.DeveloperRepository;
import com.ProjectManagementSystem.model.repositories.EntityRepository;
import com.ProjectManagementSystem.model.repositories.ProjectRepository;
import com.ProjectManagementSystem.service.DeveloperService;
import com.ProjectManagementSystem.service.ProjectService;
import com.ProjectManagementSystem.service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/developers/new")
public class DeveloperNewServlet extends HttpServlet {
    private Service<DeveloperDTO> developerService;
    private Service<ProjectDTO> projectService;

    @Override
    public void init() throws ServletException {
        EntityRepository<ProjectDAO> projectRepository = new ProjectRepository();
        EntityRepository<DeveloperDAO> developerRepository = new DeveloperRepository();
        this.developerService = new DeveloperService(developerRepository);
        this.projectService = new ProjectService(projectRepository);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<ProjectDTO> projects = projectService.findAll();
        List<String> brunches = Arrays.stream(Brunch.values()).map(Brunch::toString).collect(Collectors.toList());
        List<String> skillLevels = (Arrays.stream(SkillLevel.values()).map(SkillLevel::toString))
                .collect(Collectors.toList());
        req.setAttribute("projects", projects);
        req.setAttribute("brunches", brunches);
        req.setAttribute("skillLevels", skillLevels);
        req.setAttribute("method", "post");
        req.getRequestDispatcher("/view/developer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DeveloperDTO developerDTO = new DeveloperDTO();
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
                    .map(projectService::findById)
                    .collect(Collectors.toSet());
            developerDTO.setProjects(projects);
        }
        List<String> brunches = Arrays.stream(Brunch.values()).map(Brunch::toString).collect(Collectors.toList());
        List<String> levels = Arrays.asList(req.getParameterValues("skill_level").clone());
        Iterator<String> brIterator = brunches.iterator();
        Iterator<String> lvlIterator = levels.iterator();
        Set<SkillsDTO> devSkills = new HashSet<>();
        while (brIterator.hasNext() && lvlIterator.hasNext()) {
            SkillsDTO ds = new SkillsDTO();
            ds.setBrunch(brIterator.next());
            ds.setLevel(lvlIterator.next());
            devSkills.add(ds);
        }
        devSkills.removeIf(devSkill -> devSkill.getLevel().equalsIgnoreCase("none"));
        developerDTO.setSkills(devSkills);
        developerService.create(developerDTO);
        resp.sendRedirect(req.getContextPath() + "/developers");
    }


}
