package com.ProjectManagementSystem.service.converter;

import com.ProjectManagementSystem.model.dao.DeveloperDAO;

import java.util.HashSet;
import java.util.Set;

public interface Converter<DAO, DTO> {
    DAO fromDTO(DTO dto);

    DTO toDTO(DAO dao);

    default Set<DAO> fromDTOSet(Set<DTO> dtoSet){
        Set<DAO> daoSet = new HashSet<>();
        if (dtoSet.isEmpty()) return daoSet;
        dtoSet.forEach(dto -> daoSet.add(fromDTO(dto)));
        return daoSet;
    }

    default Set<DTO> toDTOSet(Set<DAO> daoSet){
        Set<DTO> dtoSet = new HashSet<>();
        if (daoSet.isEmpty()) return dtoSet;
        daoSet.forEach(dao -> dtoSet.add(toDTO(dao)));
        return dtoSet;
    }

}
