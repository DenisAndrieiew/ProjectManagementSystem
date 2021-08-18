package com.ProjectManagementSystem.model.repositories;

import com.ProjectManagementSystem.service.converter.Converter;


public interface Repository<T> {
    T findById(long id);

    void create(T entity);

    void update(T entity);

    void delete(long id);

    Converter getConverter();

}
