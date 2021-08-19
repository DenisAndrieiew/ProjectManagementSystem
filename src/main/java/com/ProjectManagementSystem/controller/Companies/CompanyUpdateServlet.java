//package com.ProjectManagementSystem.controller;
//
//import com.ProjectManagementSystem.dto.CompanyDTO;
//import com.ProjectManagementSystem.model.CompanyRepository;
//import com.ProjectManagementSystem.model.EntityRepository;
//import com.ProjectManagementSystem.model.dao.CompanyDAO;
//import com.ProjectManagementSystem.service.Service;
//import com.ProjectManagementSystem.service.converter.Converter;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/companies/update")
//public class CompanyUpdateServlet extends HttpServlet {
//    private EntityRepository<CompanyDAO> repository;
//    private Converter converter;
//    private Service service;
//
//    @Override
//    public void init() throws ServletException {
//        this.repository = new CompanyRepository();
//        this.converter = repository.getConverter();
//        this.service = new Service(repository);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int id = Integer.parseInt(req.getParameter("id"));
//        CompanyDTO dto = (CompanyDTO) converter.toDTO(repository.findById(id));
//        req.setAttribute("company", dto);
//        req.getRequestDispatcher("/view/companyUpdate.jsp").forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        CompanyDTO dto = new CompanyDTO();
//        dto.setId(Long.parseLong(req.getParameter("id")));
//        dto.setName(req.getParameter("name"));
//        service.update(dto);
//        resp.sendRedirect(req.getContextPath() + "/companies");
//    }
//}
