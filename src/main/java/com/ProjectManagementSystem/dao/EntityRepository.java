package com.ProjectManagementSystem.dao;

import com.ProjectManagementSystem.dao.model.DataAccessObject;

import java.util.List;

public interface EntityRepository<T extends DataAccessObject> extends Repository<T> {
    public List<T> findAll();
}
