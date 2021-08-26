package com.ProjectManagementSystem.controller.Customers;

import com.ProjectManagementSystem.dto.CustomerDTO;
import com.ProjectManagementSystem.model.dao.CustomerDAO;
import com.ProjectManagementSystem.model.repositories.CustomerRepository;
import com.ProjectManagementSystem.model.repositories.EntityRepository;
import com.ProjectManagementSystem.service.CustomerService;
import com.ProjectManagementSystem.service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/customers")
public class CustomersServlet extends HttpServlet {
    private EntityRepository<CustomerDAO> repository;
    private Service<CustomerDTO> service;

    @Override
    public void init() throws ServletException {
        this.repository = new CustomerRepository();
        this.service = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<CustomerDTO> customers = service.findAll();
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("/view/customers.jsp").forward(req, resp);
    }

}
