package com.ProjectManagementSystem.service.converter;

import com.ProjectManagementSystem.repository.model.BrunchDAO;
import com.ProjectManagementSystem.dto.BrunchDTO;
import com.ProjectManagementSystem.dto.enums.Brunch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class BrunchConverter implements Converter<BrunchDAO, BrunchDTO> {
    @Override
    public BrunchDAO fromDTO(BrunchDTO dto) {
        BrunchDAO dao = new BrunchDAO(dto.getBrunch().toString());
        if (dto.getId() != 0) dao.setId(dto.getId());
        return dao;
    }

    @Override
    public BrunchDTO toDTO(BrunchDAO dao) {
        BrunchDTO dto = new BrunchDTO();
        dto.setBrunch(Brunch.toBrunch(dao.getBrunch()).orElse(null));
        dto.setId(dao.getId());
        return (BrunchDTO) dto;
    }

    @Override
    public List<BrunchDAO> fromResultSet(ResultSet resultSet) throws SQLException {
        List<BrunchDAO> brunches = new LinkedList<>();
        while (resultSet.next()) {
            BrunchDAO dao = new BrunchDAO();
            dao.setId(resultSet.getLong("id"));
            dao.setBrunch(resultSet.getString("name"));
            brunches.add(dao);
        }
        return brunches;
    }


}
