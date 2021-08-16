package com.ProjectManagementSystem.service.converter;

import com.ProjectManagementSystem.dto.CustomersDTO;
import com.ProjectManagementSystem.repository.ProjectsRepository;
import com.ProjectManagementSystem.repository.model.CustomersDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CustomersConverter implements Converter<CustomersDAO, CustomersDTO> {
    @Override
    public CustomersDAO fromDTO(CustomersDTO dto) {
        CustomersDAO dao = new CustomersDAO();
        if (dto.getId() != 0) dao.setId(dto.getId());
        dao.setName(dto.getName());
        dao.setProjects(dto.getProjects());
        return dao;
    }

    @Override
    public CustomersDTO toDTO(CustomersDAO dao) {
        CustomersDTO dto = new CustomersDTO();
        dto.setProjects(dao.getProjects());
        dto.setId(dao.getId());
        dto.setName(dao.getName());
        return dto;
    }

    @Override
    public List<CustomersDAO> fromResultSet(ResultSet resultSet) throws SQLException {
        List<CustomersDAO> customers = new LinkedList<>();
        while (resultSet.next()) {
            CustomersDAO dao = new CustomersDAO();
            dao.setId(resultSet.getLong("id"));
            dao.setName(resultSet.getString("name"));
            Map<Long, String> projects = new HashMap<>();
            new ProjectsRepository().findByNumber("customer_id", dao.getId()).stream()
                    .forEach(projectsDAO -> projects.put(projectsDAO.getId(), projectsDAO.getName()));
            dao.setProjects(projects);
            customers.add(dao);
        }

        return customers;
    }
}
