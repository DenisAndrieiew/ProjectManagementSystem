package com.ProjectManagementSystem.controller.Companies;

import com.ProjectManagementSystem.dto.CompanyDTO;
import com.ProjectManagementSystem.model.dao.CompanyDAO;
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
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/companies")
public class CompaniesServlet extends HttpServlet {
    private EntityRepository<CompanyDAO> repository;
    private Converter converter;
    private Service service;

    @Override
    public void init() throws ServletException {
        this.repository = new GenericEntityRepository<>(CompanyDAO.class);
        this.converter = repository.getConverter();
        this.service = new Service(repository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<CompanyDTO> companies = repository.findAll().stream()
                .map(dao -> (CompanyDTO) converter.toDTO(dao))
                .sorted(Comparator.comparing(CompanyDTO::getId))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        req.setAttribute("companies", companies);
        req.getRequestDispatcher("/view/companies.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CompanyDTO dto = new CompanyDTO();
        dto.setName(req.getParameter("name"));
        service.create(dto);
        resp.sendRedirect(req.getContextPath() + "/companies");
    }
}
