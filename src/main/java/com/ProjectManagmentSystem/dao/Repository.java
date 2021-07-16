package com.ProjectManagmentSystem.dao;

import com.ProjectManagmentSystem.dao.model.DataAccessObject;
import com.ProjectManagmentSystem.service.converter.Converter;

import java.util.List;

public interface Repository<T extends DataAccessObject> {
    T findById(long id);

    List<T> findByString(String requestField, String requestText);

    List<T> findByNumber(String requestField, long requestNumber);

    void create(T entity);

    void update(T entity);

    void delete(long id);

    Converter getConverter();


}
