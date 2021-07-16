package com.ProjectManagmentSystem.service.converter;

import com.ProjectManagmentSystem.dao.model.BrunchDAO;
import com.ProjectManagmentSystem.dao.model.DeveloperDAO;
import com.ProjectManagmentSystem.dto.BrunchDTO;
import com.ProjectManagmentSystem.dto.DeveloperDTO;
import com.ProjectManagmentSystem.dto.enums.Brunch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class BrunchConverter implements Converter<BrunchDAO, BrunchDTO> {
    @Override
    public BrunchDAO fromDTO(BrunchDTO dto) {
        BrunchDAO dao = new BrunchDAO(dto.getBrunch().toString());
        return dao;
    }

    @Override
    public BrunchDTO toDTO(BrunchDAO dao) {
        BrunchDTO dto = new BrunchDTO();
        dto.setBrunch(Brunch.toBrunch(dao.getBrunch()).orElseThrow());
        return dto;
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

    public DeveloperDAO fromDTO(DeveloperDTO devDTO) {
        DeveloperDAO devDAO = new DeveloperDAO(devDTO.getFirstName(), devDTO.getLastName(),
                devDTO.getAge(), devDTO.getSex(), devDTO.getComments(), devDTO.getSalary());
        return devDAO;
    }

    public DeveloperDTO toDTO(DeveloperDAO devDAO) {
        DeveloperDTO devDTO = new DeveloperDTO(devDAO.getId(), devDAO.getFirstName(), devDAO.getLastName(),
                devDAO.getAge(), devDAO.getSex(), devDAO.getComments(), devDAO.getSalary());
        return devDTO;
    }

}
