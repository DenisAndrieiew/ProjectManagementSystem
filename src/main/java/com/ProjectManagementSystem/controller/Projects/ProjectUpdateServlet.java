//package com.ProjectManagementSystem.controller;
//
//import com.ProjectManagementSystem.dto.CompanyDTO;
//import com.ProjectManagementSystem.dto.CustomersDTO;
//import com.ProjectManagementSystem.dto.ProjectsDTO;
//import com.ProjectManagementSystem.model.CompanyRepository;
//import com.ProjectManagementSystem.model.CustomersRepository;
//import com.ProjectManagementSystem.model.EntityRepository;
//import com.ProjectManagementSystem.model.ProjectsRepository;
//import com.ProjectManagementSystem.model.dao.CompanyDAO;
//import com.ProjectManagementSystem.model.dao.CustomersDAO;
//import com.ProjectManagementSystem.model.dao.ProjectsDAO;
//import com.ProjectManagementSystem.service.Service;
//import com.ProjectManagementSystem.service.converter.Converter;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.time.Instant;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@WebServlet("/projects/update")
//public class ProjectUpdateServlet extends HttpServlet {
//    private EntityRepository<ProjectsDAO> projectRepository;
//    private Converter projectConverter;
//    private Service projectService;
//    private EntityRepository<CompanyDAO> companyRepository;
//    private Converter companyConverter;
//    private EntityRepository<CustomersDAO> customersRepository;
//    private Converter customersConverter;
//
//
//    @Override
//    public void init() throws ServletException {
//        this.projectRepository = new ProjectsRepository();
//        this.projectConverter = projectRepository.getConverter();
//        this.companyRepository = new CompanyRepository();
//        this.companyConverter = companyRepository.getConverter();
//        this.customersRepository = new CustomersRepository();
//        this.customersConverter = customersRepository.getConverter();
//        this.projectService = new Service(projectRepository);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<CompanyDTO> companies = companyRepository.findAll().stream()
//                .map((dao->(CompanyDTO) companyConverter.toDTO(dao)))
//                .collect(Collectors.toList());
//        List<CustomersDTO> customers = customersRepository.findAll().stream()
//                .map((dao->(CustomersDTO) customersConverter.toDTO(dao)))
//                .collect(Collectors.toList());
//        int id = Integer.parseInt(req.getParameter("id"));
//        ProjectsDTO project = (ProjectsDTO) projectConverter.toDTO(projectRepository.findById(id));
//        req.setAttribute("project", project);
//        req.setAttribute("companies", companies);
//        req.setAttribute("customers", customers);
//        req.getRequestDispatcher("/view/projectUpdate.jsp").forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ProjectsDTO project = new ProjectsDTO();
//        project.setId(Long.parseLong(req.getParameter("id")));
//        project.setName(req.getParameter("name"));
//        project.setDescription(req.getParameter("description"));
//        project.setBeginDate(Instant.parse(req.getParameter("beginDate")));
//        project.setCustomer(req.getParameter("customer"));
//        project.setCompany(req.getParameter("company"));
//        projectService.update(project);
//        resp.sendRedirect(req.getContextPath() + "/projects");
//    }
//}