package com.ProjectManagmentSystem.service.converter;

import com.ProjectManagmentSystem.dao.model.DeveloperDAO;
import com.ProjectManagmentSystem.dto.DeveloperDTO;
import com.ProjectManagmentSystem.dto.enums.Sex;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DeveloperConverter implements Converter<DeveloperDAO, DeveloperDTO> {

    public DeveloperDAO fromDTO(DeveloperDTO dto) {
        DeveloperDAO dao = new DeveloperDAO(dto.getFirstName(), dto.getLastName(),
                dto.getAge(), dto.getSex(), dto.getComments(), dto.getSalary());
        if (dto.getId() != 0) dao.setId(dto.getId());
        return dao;
    }

    public DeveloperDTO toDTO(DeveloperDAO dao) {
        DeveloperDTO dto = new DeveloperDTO(dao.getId(), dao.getFirstName(), dao.getLastName(),
                dao.getAge(), dao.getSex(), dao.getComments(), dao.getSalary());
        return dto;
    }


    public List<DeveloperDAO> fromResultSet(ResultSet resultSet) throws SQLException {
        List<DeveloperDAO> developers = new LinkedList<>();
        while (resultSet.next()) {
            DeveloperDAO dao = new DeveloperDAO();
            dao.setId(resultSet.getLong("id"));
            dao.setFirstName(resultSet.getString("first_name"));
            dao.setLastName(resultSet.getString("last_name"));
            dao.setAge(resultSet.getInt("age"));
            dao.setSex(Sex.valueOf(resultSet.getString("dev_sex").toUpperCase()));
            dao.setComments(resultSet.getString("comments"));
            dao.setSalary(resultSet.getInt("salary"));
            developers.add(dao);
        }
        return developers;
    }

}
