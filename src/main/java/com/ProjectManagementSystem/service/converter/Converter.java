package com.ProjectManagementSystem.service.converter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Converter<DAO, DTO> {
    DAO fromDTO(DTO dto);

    DTO toDTO(DAO dao);

    List<DAO> fromResultSet(ResultSet resultSet) throws SQLException;


}
