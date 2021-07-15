package com.ProjectManagmentSystem.service.converter;

import com.ProjectManagmentSystem.dao.model.DevSkillsDAO;
import com.ProjectManagmentSystem.dto.DevSkillsDTO;
import com.ProjectManagmentSystem.service.converter.Converter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DevSkillsConverter implements Converter<DevSkillsDAO, DevSkillsDTO> {


    @Override
    public DevSkillsDAO fromDTO(DevSkillsDTO devSkillsDTO) {
        return null;
    }

    @Override
    public DevSkillsDTO toDTO(DevSkillsDAO devSkillsDAO) {
        return null;
    }

    @Override
    public List<DevSkillsDAO> fromResultSet(ResultSet rs) throws SQLException {
        return null;
    }
}
