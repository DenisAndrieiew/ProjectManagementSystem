package com.ProjectManagementSystem.controller;

import com.ProjectManagementSystem.model.CustomersRepository;
import com.ProjectManagementSystem.model.EntityRepository;
import com.ProjectManagementSystem.model.dao.CustomersDAO;
import com.ProjectManagementSystem.service.Service;
import com.ProjectManagementSystem.service.converter.Converter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/customers/delete")
public class CustomerDeleteServlet extends HttpServlet {
    private EntityRepository<CustomersDAO> repository;
    private Converter converter;
    private Service service;

    @Override
    public void init() throws ServletException {
        this.repository = new CustomersRepository();
        this.converter = repository.getConverter();
        this.service = new Service(repository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.delete(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/customers");
    }

}

