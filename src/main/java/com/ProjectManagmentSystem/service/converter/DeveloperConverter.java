package com.ProjectManagmentSystem.service.converter;

import com.ProjectManagmentSystem.dao.model.DeveloperDAO;
import com.ProjectManagmentSystem.dto.DeveloperDTO;
import com.ProjectManagmentSystem.dto.enums.Sex;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DeveloperConverter implements Converter<DeveloperDAO, DeveloperDTO> {

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


    public List<DeveloperDAO> fromResultSet(ResultSet resultSet) throws SQLException {
        List<DeveloperDAO> developers = new LinkedList<>();
        while (resultSet.next()) {
            DeveloperDAO dev = new DeveloperDAO();
            dev.setId(resultSet.getLong("id"));
            dev.setFirstName(resultSet.getString("first_name"));
            dev.setLastName(resultSet.getString("last_name"));
            dev.setAge(resultSet.getInt("age"));
            dev.setSex(Sex.valueOf(resultSet.getString("dev_sex").toUpperCase()));
            dev.setComments(resultSet.getString("comments"));
            dev.setSalary(resultSet.getInt("salary"));
            developers.add(dev);
        }
        return developers;
    }

}
