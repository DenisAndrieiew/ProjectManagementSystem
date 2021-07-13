package com.ProjectManagmentSystem.service;

import com.ProjectManagmentSystem.dao.model.DeveloperDAO;
import com.ProjectManagmentSystem.dto.DeveloperDTO;
import com.ProjectManagmentSystem.dto.Sex;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DeveloperConverter {


    public static DeveloperDAO toDeveloper(DeveloperDTO devDTO) {
        DeveloperDAO devDAO = new DeveloperDAO(devDTO.getId(), devDTO.getFirstName(), devDTO.getLastName(),
                devDTO.getAge(), devDTO.getSex(), devDTO.getComments(), devDTO.getSalary());
        return devDAO;
    }

    public static DeveloperDTO fromDeveloper(DeveloperDAO devDAO) {
        DeveloperDTO devDTO = new DeveloperDTO(devDAO.getId(), devDAO.getFirstName(), devDAO.getLastName(),
                devDAO.getAge(), devDAO.getSex(), devDAO.getComments(), devDAO.getSalary());
        return devDTO;
    }

    public static List<DeveloperDAO> toDeveloper(ResultSet resultSet) throws SQLException {
        List<DeveloperDAO> developers = new LinkedList<>();
        while (resultSet.next()) {
            DeveloperDAO dev = new DeveloperDAO();
            dev.setId(resultSet.getLong("id"));
            dev.setFirstName(resultSet.getString("first_name"));
            dev.setLastName(resultSet.getString("last_name"));
            dev.setAge(resultSet.getInt("age"));
            dev.setSex(Sex.valueOf(resultSet.getString("dev_sex")));
            dev.setComments(resultSet.getString("comments"));
            dev.setSalary(resultSet.getInt("salary"));
            developers.add(dev);
        }
        return developers;
    }
}
