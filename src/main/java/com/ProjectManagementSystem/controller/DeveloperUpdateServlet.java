package com.ProjectManagementSystem.controller;

import com.ProjectManagementSystem.dto.BrunchDTO;
import com.ProjectManagementSystem.dto.DeveloperDTO;
import com.ProjectManagementSystem.dto.ProjectsDTO;
import com.ProjectManagementSystem.dto.enums.Sex;
import com.ProjectManagementSystem.model.BrunchRepository;
import com.ProjectManagementSystem.model.DeveloperRepository;
import com.ProjectManagementSystem.model.EntityRepository;
import com.ProjectManagementSystem.model.ProjectsRepository;
import com.ProjectManagementSystem.model.dao.BrunchDAO;
import com.ProjectManagementSystem.model.dao.DeveloperDAO;
import com.ProjectManagementSystem.model.dao.ProjectsDAO;
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
    private EntityRepository<ProjectsDAO> projectRepository;
    private Converter projectConverter;
    private EntityRepository<BrunchDAO> brunchRepository;
    private Converter brunchConverter;
    private EntityRepository<DeveloperDAO> developerRepository;
    private Converter developerConverter;
    private Service developerService;

    @Override
    public void init() throws ServletException {
        this.projectRepository = new ProjectsRepository();
        this.projectConverter = projectRepository.getConverter();
        this.brunchRepository = new BrunchRepository();
        this.brunchConverter = brunchRepository.getConverter();
        this.developerRepository = new DeveloperRepository();
        this.developerConverter = developerRepository.getConverter();
        this.developerService = new Service(developerRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProjectsDTO> projects = projectRepository.findAll().stream()
                .map(dao -> (ProjectsDTO) projectConverter.toDTO(dao))
                .collect(Collectors.toList());
        List<BrunchDTO> brunches = brunchRepository.findAll().stream()
                .map(dao -> (BrunchDTO) brunchConverter.toDTO(dao))
                .collect(Collectors.toList());
        req.setAttribute("projects", projects);
        req.setAttribute("brunches", brunches);
        req.setAttribute("method", "put");
        int id = Integer.parseInt(req.getParameter("id"));
        DeveloperDTO developer = (DeveloperDTO) developerConverter.toDTO(developerRepository.findById(id));
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
        String[] projectsFromForm = req.getParameterValues("in_project");
        developerDTO.setSalary(Integer.parseInt(req.getParameter("salary")));
        developerDTO.setComments(req.getParameter("comments"));
        if (Objects.nonNull(projectsFromForm)) {
            List<Long> projects = Arrays.asList(projectsFromForm).stream()
                    .map(Long::parseLong).collect(Collectors.toList());
            developerDTO.setProjectsIds(projects);
        }
        List<BrunchDTO> brunches = brunchRepository.findAll().stream()
                .map(dao -> (BrunchDTO) brunchConverter.toDTO(dao))
                .collect(Collectors.toList());
        List<String> levels = Arrays.asList(req.getParameterValues("skill_level").clone());
        Iterator<BrunchDTO> brIterator = brunches.iterator();
        Iterator<String> lvlIterator = levels.iterator();
        Map<String, String> skillLevels = new HashMap<>();
        while (brIterator.hasNext() && lvlIterator.hasNext()) {
            skillLevels.put(brIterator.next().getBrunch().name(), lvlIterator.next());
        }
        skillLevels.values().removeIf(value -> value.equalsIgnoreCase("none"));
        developerDTO.setSkillLevels(skillLevels);

        developerService.update(developerDTO);
        resp.sendRedirect(req.getContextPath() + "/developers");
    }
}
