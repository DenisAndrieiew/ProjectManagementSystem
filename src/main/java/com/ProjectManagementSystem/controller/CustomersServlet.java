package com.ProjectManagementSystem.controller;

import com.ProjectManagementSystem.dto.CustomerDTO;
import com.ProjectManagementSystem.model.CustomersRepository;
import com.ProjectManagementSystem.model.EntityRepository;
import com.ProjectManagementSystem.model.dao.CustomerDAO;
import com.ProjectManagementSystem.service.Service;
import com.ProjectManagementSystem.service.converter.Converter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/customers")
public class CustomersServlet extends HttpServlet {
    private EntityRepository<CustomerDAO> repository;
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
        List<CustomerDTO> customers = repository.findAll().stream()
                .map(dao -> (CustomerDTO) converter.toDTO(dao))
                .sorted(Comparator.comparing(CustomerDTO::getId))
                .collect(Collectors.toList());
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("/view/customers.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDTO dto = new CustomerDTO();
        dto.setName(req.getParameter("name"));
        service.create(dto);
        resp.sendRedirect(req.getContextPath() + "/customers");
    }
}
