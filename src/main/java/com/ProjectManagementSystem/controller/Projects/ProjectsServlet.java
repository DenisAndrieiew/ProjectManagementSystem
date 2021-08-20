package com.ProjectManagementSystem.controller.Projects;

import com.ProjectManagementSystem.dto.ProjectDTO;
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
import java.time.Instant;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/projects")
public class ProjectsServlet extends HttpServlet {
    private static EntityRepository<ProjectDAO> repository;
    private static Converter converter;
    private static Service projectService;

    @Override
    public void init() throws ServletException {
        repository = new GenericEntityRepository<>(ProjectDAO.class);
        converter = repository.getConverter();
        projectService = new Service(repository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<ProjectDTO> projects = repository.findAll().stream()
                .map(projectsDAO -> (ProjectDTO) converter.toDTO(projectsDAO))
                .sorted(Comparator.comparing(ProjectDTO::getId))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        req.setAttribute("pj", projects);
        req.getRequestDispatcher("/view/projects.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProjectDTO project = new ProjectDTO();
        project.setName(req.getParameter("name"));
        project.setDescription(req.getParameter("description"));
        project.setBeginDate(Instant.parse(req.getParameter("beginDate")));
        project.setCustomer(req.getParameter("customer"));
        project.setCompany(req.getParameter("company"));
        projectService.create(project);
        resp.sendRedirect(req.getContextPath() + "/projects");
    }
}

