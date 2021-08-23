package com.ProjectManagementSystem.controller.Companies;

import com.ProjectManagementSystem.model.dao.CompanyDAO;
import com.ProjectManagementSystem.model.repositories.EntityRepository;
import com.ProjectManagementSystem.model.repositories.GenericEntityRepository;
import com.ProjectManagementSystem.service.CompanyService;
import com.ProjectManagementSystem.service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/companies/delete")
public class CompanyDeleteServlet extends HttpServlet {
    private static EntityRepository<CompanyDAO> repository;
    private static Service service;

    @Override
    public void init() throws ServletException {
        repository = new GenericEntityRepository<>(CompanyDAO.class);
        service = new CompanyService(repository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.delete(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/companies");
    }

}
