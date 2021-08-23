package com.ProjectManagementSystem.controller.Companies;

import com.ProjectManagementSystem.dto.CompanyDTO;
import com.ProjectManagementSystem.model.dao.CompanyDAO;
import com.ProjectManagementSystem.model.repositories.CompanyRepository;
import com.ProjectManagementSystem.model.repositories.EntityRepository;
import com.ProjectManagementSystem.service.CompanyService;
import com.ProjectManagementSystem.service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/companies")
public class CompaniesServlet extends HttpServlet {
    private Service service;

    @Override
    public void init() throws ServletException {
        EntityRepository<CompanyDAO> repository = new CompanyRepository();
        this.service = new CompanyService(repository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<CompanyDTO> companies = service.findAll();
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
