package com.ProjectManagementSystem.controller.Customers;

import com.ProjectManagementSystem.dto.CustomerDTO;
import com.ProjectManagementSystem.model.dao.CustomerDAO;
import com.ProjectManagementSystem.model.repositories.EntityRepository;
import com.ProjectManagementSystem.model.repositories.GenericEntityRepository;
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

@WebServlet("/customers")
public class CustomersServlet extends HttpServlet {
    private EntityRepository<CustomerDAO> repository;
    private Converter converter;

    @Override
    public void init() throws ServletException {
        this.repository = new GenericEntityRepository<>(CustomerDAO.class);
        this.converter = repository.getConverter();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<CustomerDTO> customers = repository.findAll().stream()
                .map(dao -> (CustomerDTO) converter.toDTO(dao))
                .sorted(Comparator.comparing(CustomerDTO::getId))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("/view/customers.jsp").forward(req, resp);
    }

}
