package com.ProjectManagementSystem.dao;

import com.ProjectManagementSystem.dao.model.DataAccessObject;
import com.ProjectManagementSystem.service.converter.Converter;


public interface Repository<T extends DataAccessObject> {
    T findById(long id);

    void create(T entity);

    void update(T entity);

    void delete(long id);

    Converter getConverter();

    long getNextId()

}
