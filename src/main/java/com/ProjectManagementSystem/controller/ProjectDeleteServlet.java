package com.ProjectManagementSystem.controller;

import com.ProjectManagementSystem.repository.EntityRepository;
import com.ProjectManagementSystem.repository.ProjectsRepository;
import com.ProjectManagementSystem.repository.model.ProjectsDAO;
import com.ProjectManagementSystem.service.Service;
import com.ProjectManagementSystem.service.converter.Converter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/projects/delete")
public class ProjectDeleteServlet extends HttpServlet {
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
        projectService.delete(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/projects");
    }
}
