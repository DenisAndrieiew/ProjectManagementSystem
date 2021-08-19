package com.ProjectManagementSystem.controller.Developers;

import com.ProjectManagementSystem.dto.DeveloperDTO;
import com.ProjectManagementSystem.model.dao.DeveloperDAO;
import com.ProjectManagementSystem.model.repositories.EntityRepository;
import com.ProjectManagementSystem.model.repositories.GenericEntityRepository;
import com.ProjectManagementSystem.service.Service;
import com.ProjectManagementSystem.service.converter.Converter;
import com.ProjectManagementSystem.service.converter.DeveloperConverter;

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

@WebServlet("/developers")
public class DevelopersServlet extends HttpServlet {
    private EntityRepository<DeveloperDAO> repository;
    private Converter converter;
    private Service developerService;


    @Override
    public void init() throws ServletException {
        this.repository = new GenericEntityRepository<DeveloperDAO>(DeveloperDAO.class);
        this.converter = new DeveloperConverter();
        this.developerService = new Service(repository);
//        this.brunchRepository = new BrunchRepository();
//        this.brunchConverter = brunchRepository.getConverter();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<DeveloperDTO> developers = repository.findAll().stream()
                .map(developerDAO -> (DeveloperDTO) converter.toDTO(developerDAO))
                .sorted(Comparator.comparing(DeveloperDTO::getId))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        req.setAttribute("developers", developers);
        req.getRequestDispatcher("/view/developers.jsp").forward(req, resp);
    }

}
