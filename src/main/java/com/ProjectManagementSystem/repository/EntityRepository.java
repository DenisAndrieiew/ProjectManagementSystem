package com.ProjectManagementSystem.repository;

import com.ProjectManagementSystem.repository.model.DataAccessObject;

import java.util.List;

public interface EntityRepository<T extends DataAccessObject> extends Repository<T> {
    public List<T> findAll();
}
