package com.ProjectManagmentSystem.service.converter;

import com.ProjectManagmentSystem.dao.model.CustomersDAO;
import com.ProjectManagmentSystem.dto.CustomersDTO;
import com.ProjectManagmentSystem.service.converter.Converter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CustomersConverter implements Converter<CustomersDAO, CustomersDTO> {
    @Override
    public CustomersDAO fromDTO(CustomersDTO dto) {
        return new CustomersDAO(dto.getName());
    }

    @Override
    public CustomersDTO toDTO(CustomersDAO dao) {
        return new CustomersDTO(dao.getId(), dao.getName());
    }

    @Override
    public List<CustomersDAO> fromResultSet(ResultSet resultSet) throws SQLException {
        List<CustomersDAO> customers = new LinkedList<>();
        while (resultSet.next()){
            CustomersDAO dao = new CustomersDAO();
            dao.setId(resultSet.getLong("id"));
            dao.setName(resultSet.getString("name"));
            customers.add(dao);
        }
        return customers;
    }
}
