package com.ProjectManagementSystem.controller.Customers;

import com.ProjectManagementSystem.dto.CustomerDTO;
import com.ProjectManagementSystem.model.dao.CustomerDAO;
import com.ProjectManagementSystem.model.repositories.EntityRepository;
import com.ProjectManagementSystem.model.repositories.GenericEntityRepository;
import com.ProjectManagementSystem.service.CustomerService;
import com.ProjectManagementSystem.service.Service;
import com.ProjectManagementSystem.service.converter.Converter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/customers/update")
public class CustomerUpdateServlet extends HttpServlet {
    private static EntityRepository<CustomerDAO> repository;
    private static Converter<CustomerDAO, CustomerDTO> converter;
    private static Service service;

    @Override
    public void init() throws ServletException {
        repository = new GenericEntityRepository<>(CustomerDAO.class);
        converter = repository.getConverter();
        service = new CustomerService(repository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        CustomerDTO dto = converter.toDTO(repository.findById(id));
        req.setAttribute("customer", dto);
        req.getRequestDispatcher("/view/customerUpdate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(Integer.parseInt(req.getParameter("id")));
        dto.setName(req.getParameter("name"));
        service.update(dto);
        resp.sendRedirect(req.getContextPath() + "/customers");
    }
}
