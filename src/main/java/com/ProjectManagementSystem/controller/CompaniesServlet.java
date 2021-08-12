package com.ProjectManagementSystem.controller;

import com.ProjectManagementSystem.dao.DeveloperRepository;
import com.ProjectManagementSystem.dao.EntityRepository;
import com.ProjectManagementSystem.dao.model.DeveloperDAO;
import com.ProjectManagementSystem.dto.DeveloperDTO;
import com.ProjectManagementSystem.service.converter.Converter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/companies")
public class CompaniesServlet extends HttpServlet {
    private EntityRepository<DeveloperDAO> repository;
    private Converter converter;

    @Override
    public void init() throws ServletException {
        this.repository = new DeveloperRepository();
        this.converter=repository.getConverter();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DeveloperDTO> developers = repository.findAll().stream()
                .map(developerDAO -> (DeveloperDTO) converter.toDTO(developerDAO))
                .collect(Collectors.toList());
        req.setAttribute("developers", developers);
        req.getRequestDispatcher("/view/companies.jsp").forward(req, resp);
    }
}
