package com.ProjectManagmentSystem.service.converter;

import com.ProjectManagmentSystem.dao.model.DevSkillsDAO;
import com.ProjectManagmentSystem.dao.model.SkillLevelDAO;
import com.ProjectManagmentSystem.dto.SkillLevelDTO;
import com.ProjectManagmentSystem.dto.enums.SkillLevel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SkillLevelConverter implements Converter<SkillLevelDAO, SkillLevelDTO> {
    public SkillLevelDAO fromDTO(SkillLevelDTO dto) {
        SkillLevelDAO dao = new SkillLevelDAO(dto.getLevel().toString());
        return dao;
    }

    public SkillLevelDTO toDTO(SkillLevelDAO dao) {
        SkillLevelDTO dto = new SkillLevelDTO(dao.getId(), SkillLevel.toSkillLevel(dao.getName()).orElseThrow());
        return dto;
    }


    public List<SkillLevelDAO> fromResultSet(ResultSet resultSet) throws SQLException {
        List<SkillLevelDAO> skillLevels= new LinkedList<>();
        while (resultSet.next()) {
            SkillLevelDAO dao = new SkillLevelDAO();
            dao.setId(resultSet.getLong("id"));
            dao.setName(resultSet.getString("name"));
            skillLevels.add(dao);
        }
        return skillLevels;
    }
}
