package com.ProjectManagementSystem.controller.Projects;

import com.ProjectManagementSystem.dto.CompanyDTO;
import com.ProjectManagementSystem.dto.CustomerDTO;
import com.ProjectManagementSystem.dto.ProjectDTO;
import com.ProjectManagementSystem.model.dao.CompanyDAO;
import com.ProjectManagementSystem.model.dao.CustomerDAO;
import com.ProjectManagementSystem.model.dao.ProjectDAO;
import com.ProjectManagementSystem.model.repositories.EntityRepository;
import com.ProjectManagementSystem.model.repositories.GenericEntityRepository;
import com.ProjectManagementSystem.service.ProjectService;
import com.ProjectManagementSystem.service.Service;
import com.ProjectManagementSystem.service.converter.Converter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/projects/new")
public class ProjectNewServlet extends HttpServlet {
    private static EntityRepository<CompanyDAO> companyRepository;
    private static Converter companyConverter;
    private static EntityRepository<CustomerDAO> customersRepository;
    private static Converter customersConverter;
    private static Service projectService;
    private static EntityRepository<ProjectDAO> projectRepository;

    @Override
    public void init() throws ServletException {
        companyRepository = new GenericEntityRepository<>(CompanyDAO.class);
        companyConverter = companyRepository.getConverter();
        customersRepository = new GenericEntityRepository<>(CustomerDAO.class);
        customersConverter = customersRepository.getConverter();
        projectRepository = new GenericEntityRepository<>(ProjectDAO.class);
        projectService = new ProjectService(projectRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<CustomerDAO> customers = customersRepository.findAll();
        Set<CustomerDTO> customerDTO = customers.stream()
                .map((dao -> (CustomerDTO) customersConverter.toDTO(dao)))
                .collect(Collectors.toSet());
        Set<CompanyDTO> companies = companyRepository.findAll().stream()
                .map((dao -> (CompanyDTO) companyConverter.toDTO(dao)))
                .collect(Collectors.toSet());
        req.setAttribute("customers", customerDTO);
        req.setAttribute("companies", companies);
        req.setAttribute("method", "post");
        req.getRequestDispatcher("/view/project.jsp").forward(req, resp);
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
