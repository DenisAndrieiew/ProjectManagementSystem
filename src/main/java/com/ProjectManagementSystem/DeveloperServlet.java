package com.ProjectManagementSystem;

import com.ProjectManagementSystem.dao.DeveloperRepository;
import com.ProjectManagementSystem.dao.EntityRepository;
import com.ProjectManagementSystem.dao.model.DeveloperDAO;
import com.ProjectManagementSystem.jdbc.config.DatabaseConnectionManager;
import com.ProjectManagementSystem.service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/developers")
public class DeveloperServlet extends HttpServlet {
    private EntityRepository<DeveloperDAO> repository;
    DatabaseConnectionManager manager = new DatabaseConnectionManager();


    @Override
    public void init() throws ServletException {
        this.repository = new DeveloperRepository(new DatabaseConnectionManager());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DeveloperDAO> developers = repository.findAll();
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
