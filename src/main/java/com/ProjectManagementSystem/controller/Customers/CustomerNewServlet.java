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

@WebServlet("/customers/new")
public class CustomerNewServlet extends HttpServlet {
    private Service service;

    @Override
    public void init() throws ServletException {
        EntityRepository<CustomerDAO> repository = new CustomerRepository();
        this.service = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/customer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDTO dto = new CustomerDTO();
        dto.setName(req.getParameter("name"));
        service.create(dto);
        resp.sendRedirect(req.getContextPath() + "/customers");
    }
}

