package com.ProjectManagmentSystem.service.converter;

import com.ProjectManagmentSystem.dao.model.CustomersDAO;
import com.ProjectManagmentSystem.dto.CustomersDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CustomersConverter implements Converter<CustomersDAO, CustomersDTO> {
    @Override
    public CustomersDAO fromDTO(CustomersDTO dto) {
        CustomersDAO dao = new CustomersDAO(dto.getName());
        if (dto.getId() != 0) dao.setId(dto.getId());
        return dao;
    }

    @Override
    public CustomersDTO toDTO(CustomersDAO dao) {
        return new CustomersDTO(dao.getId(), dao.getName());
    }

    @Override
    public List<CustomersDAO> fromResultSet(ResultSet resultSet) throws SQLException {
        List<CustomersDAO> customers = new LinkedList<>();
        while (resultSet.next()) {
            CustomersDAO dao = new CustomersDAO();
            dao.setId(resultSet.getLong("id"));
            dao.setName(resultSet.getString("name"));
            customers.add(dao);
        }
        return customers;
    }
}
