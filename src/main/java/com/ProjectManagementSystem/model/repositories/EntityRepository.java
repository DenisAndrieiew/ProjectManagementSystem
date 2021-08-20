package com.ProjectManagementSystem.model.repositories;

import java.util.Set;

public interface EntityRepository<T> extends Repository<T> {
    Set<T> findAll();

    T findByUniqueName(String param, String value);
     T findEnum(String param, Object value);
}
