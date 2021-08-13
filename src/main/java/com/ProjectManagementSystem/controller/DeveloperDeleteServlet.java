package com.ProjectManagementSystem.controller;

import com.ProjectManagementSystem.dao.DeveloperRepository;
import com.ProjectManagementSystem.dao.EntityRepository;
import com.ProjectManagementSystem.dao.model.DeveloperDAO;
import com.ProjectManagementSystem.service.Service;
import com.ProjectManagementSystem.service.converter.Converter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/developers/delete")
public class DeveloperDeleteServlet extends HttpServlet {
    private EntityRepository<DeveloperDAO> repository;
    private Converter converter;
    private Service developerService;

    @Override
    public void init() throws ServletException {
        this.repository = new DeveloperRepository();
        this.converter = repository.getConverter();
        this.developerService=new Service(repository);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        developerService.delete(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/developers");
    }
}
