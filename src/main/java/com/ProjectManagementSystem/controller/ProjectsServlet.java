package com.ProjectManagementSystem.controller;

import com.ProjectManagementSystem.repository.EntityRepository;
import com.ProjectManagementSystem.repository.ProjectsRepository;
import com.ProjectManagementSystem.repository.model.ProjectsDAO;
import com.ProjectManagementSystem.dto.ProjectsDTO;
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
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/projects")
public class ProjectsServlet extends HttpServlet {
    private EntityRepository<ProjectsDAO> repository;
    private Converter converter;
    private Service projectService;

    @Override
    public void init() throws ServletException {
        this.repository = new ProjectsRepository();
        this.converter = repository.getConverter();
        this.projectService = new Service(repository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProjectsDTO> projects = repository.findAll().stream()
                .map(projectsDAO -> (ProjectsDTO) converter.toDTO(projectsDAO))
                .sorted(Comparator.comparing(ProjectsDTO::getId))
                .collect(Collectors.toList());
        req.setAttribute("pj", projects);
        req.getRequestDispatcher("/view/projects.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProjectsDTO project = new ProjectsDTO();
        project.setName(req.getParameter("name"));
        project.setDescription(req.getParameter("description"));
        project.setBeginDate(Instant.parse(req.getParameter("beginDate")));
        project.setCustomer(req.getParameter("customer"));
        project.setCompany(req.getParameter("company"));
        projectService.create(project);
        resp.sendRedirect(req.getContextPath() + "/projects");

    }
}

