package com.ProjectManagementSystem.controller.Projects;

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

@WebServlet("/projects/delete")
public class ProjectDeleteServlet extends HttpServlet {
    private static Converter converter;
    private static Service projectService;

    @Override
    public void init() throws ServletException {
        EntityRepository<ProjectDAO> repository = new GenericEntityRepository<>(ProjectDAO.class);
        converter = repository.getConverter();
        projectService = new Service(repository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        projectService.delete(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/projects");
    }
}
