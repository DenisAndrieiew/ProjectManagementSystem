package com.ProjectManagementSystem.service.converter;

import com.ProjectManagementSystem.model.dao.SkillLevelDAO;
import com.ProjectManagementSystem.dto.SkillLevelDTO;
import com.ProjectManagementSystem.dto.enums.SkillLevel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SkillLevelConverter implements Converter<SkillLevelDAO, SkillLevelDTO> {
    public SkillLevelDAO fromDTO(SkillLevelDTO dto) {
        SkillLevelDAO dao = new SkillLevelDAO();
        dto.getLevel().toString();
        if (dto.getId() != 0) dao.setId(dto.getId());
        return dao;
    }

    public SkillLevelDTO toDTO(SkillLevelDAO dao) {
        SkillLevelDTO dto = new SkillLevelDTO(dao.getId(), SkillLevel.toSkillLevel(dao.getLevel().toString()).orElse(null));
        return dto;
    }


    public List<SkillLevelDAO> fromResultSet(ResultSet resultSet) throws SQLException {
        List<SkillLevelDAO> skillLevels = new LinkedList<>();
        while (resultSet.next()) {
            SkillLevelDAO dao = new SkillLevelDAO();
            dao.setId(resultSet.getLong("id"));
            dao.setLevel(resultSet.getString("name"));
            skillLevels.add(dao);
        }
        return skillLevels;
    }
}
