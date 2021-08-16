package com.ProjectManagementSystem.repository.model;

import java.util.Map;

public class CustomersDAO implements DataAccessObject {
    private long id;
    private String name;
    private Map<Long, String> projects;

    public CustomersDAO() {
    }

    public CustomersDAO(String name) {
        this.name = name;
    }

    public CustomersDAO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Map<Long, String> getProjects() {
        return projects;
    }

    public void setProjects(Map<Long, String> projects) {
        this.projects = projects;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
