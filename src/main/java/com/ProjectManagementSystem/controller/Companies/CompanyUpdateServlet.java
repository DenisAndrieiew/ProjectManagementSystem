package com.ProjectManagementSystem.controller.Companies;

import com.ProjectManagementSystem.dto.CompanyDTO;
import com.ProjectManagementSystem.model.dao.CompanyDAO;
import com.ProjectManagementSystem.model.repositories.CompanyRepository;
import com.ProjectManagementSystem.model.repositories.EntityRepository;
import com.ProjectManagementSystem.service.CompanyService;
import com.ProjectManagementSystem.service.Service;
import com.ProjectManagementSystem.service.converter.CompanyConverter;
import com.ProjectManagementSystem.service.converter.Converter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/companies/update")
public class CompanyUpdateServlet extends HttpServlet {
    private static EntityRepository<CompanyDAO> repository;
    private static Converter<CompanyDAO, CompanyDTO> converter;
    private static Service<CompanyDTO> service;

    @Override
    public void init() throws ServletException {
        repository = new CompanyRepository();
        converter = new CompanyConverter();
        service = new CompanyService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        CompanyDTO dto = converter.toDTO(repository.findById(id));
        req.setAttribute("company", dto);
        req.getRequestDispatcher("/view/companyUpdate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CompanyDTO dto = new CompanyDTO();
        dto.setId(Integer.parseInt(req.getParameter("id")));
        dto.setName(req.getParameter("name"));
        service.update(dto);
        resp.sendRedirect(req.getContextPath() + "/companies");
    }
}
