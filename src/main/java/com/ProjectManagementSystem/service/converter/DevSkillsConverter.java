package com.ProjectManagementSystem.service.converter;

import com.ProjectManagementSystem.model.dao.DevSkillsDAO;
import com.ProjectManagementSystem.dto.DevSkillsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DevSkillsConverter implements Converter<DevSkillsDAO, DevSkillsDTO> {


    public DevSkillsDAO fromDTO(DevSkillsDTO dto) {
        DevSkillsDAO dao = new DevSkillsDAO(dto.getDevId(), dto.getSkillId(),
                dto.getSkillLevel());
        if (dto.getId() != 0) dao.setId(dto.getId());
        return dao;
    }

    public DevSkillsDTO toDTO(DevSkillsDAO dao) {
        DevSkillsDTO dto = new DevSkillsDTO(dao.getDevId(), dao.getSkillId(), dao.getSkillLevel());
        return dto;
    }


    public List<DevSkillsDAO> fromResultSet(ResultSet resultSet) throws SQLException {
        List<DevSkillsDAO> devSkillsDAOList = new LinkedList<>();
        while (resultSet.next()) {
            DevSkillsDAO dao = new DevSkillsDAO();
            dao.setId(resultSet.getLong("id"));
            dao.setDevId(resultSet.getLong("dev_id"));
            dao.setSkillId(resultSet.getLong("skill_id"));
            dao.setSkillLevel(resultSet.getLong("skill_level"));
            devSkillsDAOList.add(dao);
        }
        return devSkillsDAOList;
    }
}
