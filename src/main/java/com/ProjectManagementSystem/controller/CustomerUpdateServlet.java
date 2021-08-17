package com.ProjectManagementSystem.controller;

import com.ProjectManagementSystem.dto.CustomersDTO;
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

@WebServlet("/customers/update")
public class CustomerUpdateServlet extends HttpServlet {
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
        int id = Integer.parseInt(req.getParameter("id"));
        CustomersDTO dto = (CustomersDTO) converter.toDTO(repository.findById(id));
        req.setAttribute("customer", dto);
        req.getRequestDispatcher("/view/customerUpdate.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomersDTO dto = new CustomersDTO();
        dto.setId(Long.parseLong(req.getParameter("id")));
        dto.setName(req.getParameter("name"));
        service.update(dto);
        resp.sendRedirect(req.getContextPath() + "/customers");
    }
}
