package com.ProjectManagementSystem.model.repositories;

import java.util.Set;

public interface NewEntityRepository<T> extends NewRepository<T> {
    Set<T> FindAll();
}
