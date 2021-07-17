package com.ProjectManagmentSystem.service.converter;

import com.ProjectManagmentSystem.dao.model.CompanyDAO;
import com.ProjectManagmentSystem.dto.CompanyDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CompanyConverter implements Converter<CompanyDAO, CompanyDTO> {
    @Override
    public CompanyDAO fromDTO(CompanyDTO dto) {
        CompanyDAO dao = new CompanyDAO(dto.getName());
        if (dto.getId() != 0) dao.setId(dto.getId());
        return dao;
    }

    @Override
    public CompanyDTO toDTO(CompanyDAO dao) {
        return new CompanyDTO(dao.getId(), dao.getName());
    }

    @Override
    public List<CompanyDAO> fromResultSet(ResultSet resultSet) throws SQLException {
        List<CompanyDAO> companies = new LinkedList<>();
        while (resultSet.next()) {
            CompanyDAO dao = new CompanyDAO();
            dao.setId(resultSet.getLong("id"));
            dao.setName(resultSet.getString("name"));
            companies.add(dao);
        }
        return companies;
    }
}
