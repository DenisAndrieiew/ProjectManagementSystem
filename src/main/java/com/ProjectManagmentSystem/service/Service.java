package com.ProjectManagmentSystem.service;

public interface Service<DTO> {
    DTO create(DTO dto);
    DTO update(DTO dto);
    void delete(long id);
}
