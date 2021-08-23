package com.ProjectManagementSystem.controller.Projects;

import com.ProjectManagementSystem.dto.CompanyDTO;
import com.ProjectManagementSystem.dto.CustomerDTO;
import com.ProjectManagementSystem.dto.ProjectDTO;
import com.ProjectManagementSystem.model.dao.CompanyDAO;
import com.ProjectManagementSystem.model.dao.CustomerDAO;
import com.ProjectManagementSystem.model.dao.ProjectDAO;
import com.ProjectManagementSystem.model.repositories.*;
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
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/projects/update")
public class ProjectUpdateServlet extends HttpServlet {
    private static EntityRepository<ProjectDAO> projectRepository;
    private static Converter<ProjectDAO, ProjectDTO> projectConverter;
    private static Service<ProjectDTO> projectService;
    private static EntityRepository<CompanyDAO> companyRepository;
    private static Converter<CompanyDAO, CompanyDTO> companyConverter;
    private static EntityRepository<CustomerDAO> customersRepository;
    private static Converter<CustomerDAO, CustomerDTO> customersConverter;


    @Override
    public void init() throws ServletException {
        projectRepository = new ProjectRepository();
        projectConverter = projectRepository.getConverter();
        companyRepository = new CompanyRepository();
        companyConverter = companyRepository.getConverter();
        customersRepository = new CustomerRepository();
        customersConverter = customersRepository.getConverter();
        projectService = new ProjectService(projectRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CompanyDTO> companies = companyRepository.findAll().stream()
                .map((dao -> companyConverter.toDTO(dao)))
                .collect(Collectors.toList());
        List<CustomerDTO> customers = customersRepository.findAll().stream()
                .map((dao -> customersConverter.toDTO(dao)))
                .collect(Collectors.toList());
        int id = Integer.parseInt(req.getParameter("id"));
        ProjectDTO project = projectConverter.toDTO(projectRepository.findById(id));
        req.setAttribute("project", project);
        req.setAttribute("companies", companies);
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("/view/projectUpdate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProjectDTO project = new ProjectDTO();
        project.setId(Integer.parseInt(req.getParameter("id")));
        project.setName(req.getParameter("name"));
        project.setDescription(req.getParameter("description"));
        project.setBeginDate(Instant.parse(req.getParameter("beginDate")));
        project.setCustomer(req.getParameter("customer"));
        project.setCompany(req.getParameter("company"));
        projectService.update(project);
        resp.sendRedirect(req.getContextPath() + "/projects");
    }
}