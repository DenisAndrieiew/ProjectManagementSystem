package com.ProjectManagementSystem.model.repositories;

import com.ProjectManagementSystem.service.converter.Converter;


public interface Repository<T> {
    T findById(int id);

    void create(T entity);

    void update(T entity);

    void delete(int id);

    Converter getConverter();

}
