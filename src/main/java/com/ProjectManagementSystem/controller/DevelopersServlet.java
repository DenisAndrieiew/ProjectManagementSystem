package com.ProjectManagementSystem.controller;

import com.ProjectManagementSystem.repository.DeveloperRepository;
import com.ProjectManagementSystem.repository.EntityRepository;
import com.ProjectManagementSystem.repository.model.DeveloperDAO;
import com.ProjectManagementSystem.dto.DeveloperDTO;
import com.ProjectManagementSystem.dto.enums.Sex;
import com.ProjectManagementSystem.service.Service;
import com.ProjectManagementSystem.service.converter.Converter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@WebServlet("/developers")
public class DevelopersServlet extends HttpServlet {
    private EntityRepository<DeveloperDAO> repository;
    private Converter converter;
    private Service developerService;

    @Override
    public void init() throws ServletException {
        this.repository = new DeveloperRepository();
        this.converter = repository.getConverter();
        this.developerService = new Service(repository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DeveloperDTO> developers = repository.findAll().stream()
                .map(developerDAO -> (DeveloperDTO) converter.toDTO(developerDAO))
                .sorted(Comparator.comparing(DeveloperDTO::getId))
                .collect(Collectors.toList());
        req.setAttribute("developers", developers);
        req.getRequestDispatcher("/view/developers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DeveloperDTO developerDTO = new DeveloperDTO();
        developerDTO.setFirstName(req.getParameter("firstName"));
        developerDTO.setLastName(req.getParameter("lastName"));
        developerDTO.setAge(Integer.parseInt(req.getParameter("age")));
        developerDTO.setSex(Sex.valueOf(req.getParameter("sex")));
        String[] projectsFromForm = req.getParameterValues("in_project");
        developerDTO.setSalary(Integer.parseInt(req.getParameter("salary")));
        if (Objects.nonNull(projectsFromForm)) {
            List<String> projects = Arrays.asList(projectsFromForm);
            developerDTO.setProjects(projects);
        }
        developerService.create(developerDTO);
        resp.sendRedirect(req.getContextPath() + "/developers");
    }


}
