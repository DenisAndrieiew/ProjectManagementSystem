package com.ProjectManagementSystem.controller;

import com.ProjectManagementSystem.dao.DeveloperRepository;
import com.ProjectManagementSystem.dao.EntityRepository;
import com.ProjectManagementSystem.dao.model.DeveloperDAO;
import com.ProjectManagementSystem.dto.DeveloperDTO;
import com.ProjectManagementSystem.jdbc.config.DatabaseConnectionManager;
import com.ProjectManagementSystem.service.Service;
import com.ProjectManagementSystem.service.converter.Converter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/developers")
public class DeveloperServlet extends HttpServlet {
    private EntityRepository<DeveloperDAO> repository;
private Converter converter;

    @Override
    public void init() throws ServletException {
        this.repository = new DeveloperRepository();
        this.converter=repository.getConverter();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DeveloperDTO> developers = repository.findAll().stream()
                .map(developerDAO -> (DeveloperDTO) converter.toDTO(developerDAO))
                .collect(Collectors.toList());
        req.setAttribute("developers", developers);
        req.getRequestDispatcher("/view/developers.jsp").forward(req, resp);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        BookDTO book = new BookDTO();
//        book.setAuthor(req.getParameter("author"));
//        book.setName(req.getParameter("name"));
//        book.setCountPages(Integer.parseInt(req.getParameter("countPages")));
//        book.setPublicationYear(Integer.parseInt(req.getParameter("publicationYear")));
//        book.setDescription(req.getParameter("description"));
//        book.setGenre(Genre.valueOf(req.getParameter("genre")));
//        bookService.addBook(book);
//        req.getRequestDispatcher("/view/index.jsp").forward(req, resp);
//    }
}
