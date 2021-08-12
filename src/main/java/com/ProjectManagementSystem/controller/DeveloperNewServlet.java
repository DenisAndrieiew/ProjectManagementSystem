package com.ProjectManagementSystem.controller;

import com.ProjectManagementSystem.dao.BrunchRepository;
import com.ProjectManagementSystem.dao.DeveloperRepository;
import com.ProjectManagementSystem.dao.EntityRepository;
import com.ProjectManagementSystem.dao.ProjectsRepository;
import com.ProjectManagementSystem.dao.model.BrunchDAO;
import com.ProjectManagementSystem.dao.model.DeveloperDAO;
import com.ProjectManagementSystem.dao.model.ProjectsDAO;
import com.ProjectManagementSystem.dto.BrunchDTO;
import com.ProjectManagementSystem.dto.DeveloperDTO;
import com.ProjectManagementSystem.dto.ProjectsDTO;
import com.ProjectManagementSystem.service.converter.Converter;
import com.ProjectManagementSystem.service.converter.ProjectsConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/developers/new")
public class DeveloperNewServlet extends HttpServlet {
    private EntityRepository<ProjectsDAO> projectRepository;
    private Converter projectConverter;
    private EntityRepository<BrunchDAO> brunchRepository;
    private Converter brunchConverter;

    @Override
    public void init() throws ServletException {
        this.projectRepository = new ProjectsRepository();
        this.projectConverter= projectRepository.getConverter();
        this.brunchRepository=new BrunchRepository();
        this.brunchConverter=brunchRepository.getConverter();
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
        req.setAttribute("method", "post");
        req.getRequestDispatcher("/view/developer.jsp").forward(req, resp);
    }
}
