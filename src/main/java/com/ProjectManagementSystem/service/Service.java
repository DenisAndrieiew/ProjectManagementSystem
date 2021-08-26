package com.ProjectManagementSystem.service;

import java.util.Set;

public interface Service<T> {
    public void create(T dto);

    public void update(T dto);

    public void delete(int id);

    public Set<T> findAll();

    public T findById(int id);


}
