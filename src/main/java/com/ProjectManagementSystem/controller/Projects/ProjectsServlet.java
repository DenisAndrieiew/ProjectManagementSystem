package com.ProjectManagementSystem.controller.Projects;

import com.ProjectManagementSystem.dto.ProjectDTO;
import com.ProjectManagementSystem.model.dao.ProjectDAO;
import com.ProjectManagementSystem.model.repositories.EntityRepository;
import com.ProjectManagementSystem.model.repositories.GenericEntityRepository;
import com.ProjectManagementSystem.model.repositories.ProjectRepository;
import com.ProjectManagementSystem.service.ProjectService;
import com.ProjectManagementSystem.service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Set;

@WebServlet("/projects")
public class ProjectsServlet extends HttpServlet {
    private static EntityRepository<ProjectDAO> repository;
    private static Service service;

    @Override
    public void init() throws ServletException {
        repository = new ProjectRepository();
        service = new ProjectService(repository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<ProjectDTO> projects = service.findAll();
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
        service.create(project);
        resp.sendRedirect(req.getContextPath() + "/projects");
    }
}

