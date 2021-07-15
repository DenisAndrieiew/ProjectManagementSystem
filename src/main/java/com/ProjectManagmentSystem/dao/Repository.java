package com.ProjectManagmentSystem.dao;

import com.ProjectManagmentSystem.service.converter.Converter;

public interface Repository<T> {
    T findById(long id);
    void create(T entity);

    void update(T entity);

    void delete(long id);
    Converter getConverter();
}
