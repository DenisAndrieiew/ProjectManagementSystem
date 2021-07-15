package com.ProjectManagmentSystem.dao;

import com.ProjectManagmentSystem.dao.model.DataAccessObject;
import com.ProjectManagmentSystem.service.converter.Converter;

public interface Repository<T extends DataAccessObject> {
    T findById(long id);

    void create(T entity);

    void update(T entity);

    void delete(long id);

    Converter getConverter();

}
