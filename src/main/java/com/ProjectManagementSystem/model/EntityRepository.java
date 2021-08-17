package com.ProjectManagementSystem.model;

import com.ProjectManagementSystem.model.dao.DataAccessObject;

import java.util.List;

public interface EntityRepository<T extends DataAccessObject> extends Repository<T> {
    public List<T> findAll();
}
