package com.ProjectManagementSystem.controller.Developers;

import com.ProjectManagementSystem.dto.DeveloperDTO;
import com.ProjectManagementSystem.model.dao.DeveloperDAO;
import com.ProjectManagementSystem.model.repositories.DeveloperRepository;
import com.ProjectManagementSystem.model.repositories.EntityRepository;
import com.ProjectManagementSystem.service.DeveloperService;
import com.ProjectManagementSystem.service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/developers")
public class DevelopersServlet extends HttpServlet {
    private EntityRepository<DeveloperDAO> repository;
    private Service developerService;


    @Override
    public void init() throws ServletException {
        this.repository = new DeveloperRepository();
        this.developerService = new DeveloperService();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<DeveloperDTO> developers = developerService.findAll();
        req.setAttribute("developers", developers);
        req.getRequestDispatcher("/view/developers.jsp").forward(req, resp);
    }

}
