package com.ProjectManagementSystem.model.repositories;

import com.ProjectManagementSystem.model.dao.DataAccessObject;

import java.util.Optional;

public interface NewRepository<T> {
    Optional<T> findById(long id);

    void create(T entity);

    void update(T entity);

    void delete(long id);
}
