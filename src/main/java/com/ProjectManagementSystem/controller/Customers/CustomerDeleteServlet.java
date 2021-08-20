package com.ProjectManagementSystem.controller.Customers;

import com.ProjectManagementSystem.model.dao.CustomerDAO;
import com.ProjectManagementSystem.model.repositories.EntityRepository;
import com.ProjectManagementSystem.model.repositories.GenericEntityRepository;
import com.ProjectManagementSystem.service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/customers/delete")
public class CustomerDeleteServlet extends HttpServlet {
    private static EntityRepository<CustomerDAO> repository;
    private static Service service;

    @Override
    public void init() throws ServletException {
        repository = new GenericEntityRepository<>(CustomerDAO.class);
        service = new Service(repository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.delete(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/customers");
    }

}

