package com.ProjectManagementSystem.controller.Developers;

import com.ProjectManagementSystem.model.dao.DeveloperDAO;
import com.ProjectManagementSystem.model.repositories.EntityRepository;
import com.ProjectManagementSystem.model.repositories.GenericEntityRepository;
import com.ProjectManagementSystem.service.DeveloperService;
import com.ProjectManagementSystem.service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/developers/delete")
public class DeveloperDeleteServlet extends HttpServlet {
    private EntityRepository<DeveloperDAO> repository;
    private Service developerService;

    @Override
    public void init() throws ServletException {
        this.repository = new GenericEntityRepository<>(DeveloperDAO.class);
        this.developerService = new DeveloperService(repository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        developerService.delete(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/developers");
    }
}
