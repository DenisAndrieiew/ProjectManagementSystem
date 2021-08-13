package com.ProjectManagementSystem.controller;

import com.ProjectManagementSystem.dao.DeveloperRepository;
import com.ProjectManagementSystem.dao.EntityRepository;
import com.ProjectManagementSystem.dao.ProjectsRepository;
import com.ProjectManagementSystem.dao.model.DeveloperDAO;
import com.ProjectManagementSystem.dao.model.ProjectsDAO;
import com.ProjectManagementSystem.dto.DeveloperDTO;
import com.ProjectManagementSystem.dto.ProjectsDTO;
import com.ProjectManagementSystem.service.Service;
import com.ProjectManagementSystem.service.converter.Converter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/projects")
public class ProjectsServlet extends HttpServlet {
    private EntityRepository<ProjectsDAO> repository;
    private Converter converter;
    private Service developerService;

    @Override
    public void init() throws ServletException {
        this.repository = new ProjectsRepository();
        this.converter = repository.getConverter();
        this.developerService = new Service(repository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProjectsDTO> projects = repository.findAll().stream()
                .map(projectsDAO -> (ProjectsDTO) converter.toDTO(projectsDAO))
                .sorted(Comparator.comparing(ProjectsDTO::getId))
                .collect(Collectors.toList());
        req.setAttribute("projects", projects);
        req.getRequestDispatcher("/view/projects.jsp").forward(req, resp);
    }
}

