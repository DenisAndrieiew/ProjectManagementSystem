package com.ProjectManagementSystem.controller;

import com.ProjectManagementSystem.repository.CompanyRepository;
import com.ProjectManagementSystem.repository.CustomersRepository;
import com.ProjectManagementSystem.repository.EntityRepository;
import com.ProjectManagementSystem.repository.model.CompanyDAO;
import com.ProjectManagementSystem.repository.model.CustomersDAO;
import com.ProjectManagementSystem.dto.CompanyDTO;
import com.ProjectManagementSystem.dto.CustomersDTO;
import com.ProjectManagementSystem.service.converter.Converter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/projects/new")
public class ProjectNewServlet extends HttpServlet {
    private EntityRepository<CompanyDAO> companyRepository;
    private Converter companyConverter;
    private EntityRepository<CustomersDAO> customersRepository;
    private Converter customersConverter;

    @Override
    public void init() throws ServletException {
        this.companyRepository = new CompanyRepository();
        this.companyConverter = companyRepository.getConverter();
        this.customersRepository = new CustomersRepository();
        this.customersConverter = customersRepository.getConverter();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CustomersDAO> customers = customersRepository.findAll();
        List<CustomersDTO>customersDTO = customers.stream()
                .map((dao->(CustomersDTO) customersConverter.toDTO(dao)))
                .collect(Collectors.toList());
        List<CompanyDTO> companies = companyRepository.findAll().stream()
                .map((dao->(CompanyDTO) companyConverter.toDTO(dao)))
                .collect(Collectors.toList());
        req.setAttribute("customers", customersDTO);
        req.setAttribute("companies", companies);
        req.setAttribute("method", "post");
        req.getRequestDispatcher("/view/project.jsp").forward(req, resp);
    }
}
