package com.ProjectManagementSystem.model.dao;

import java.util.Map;

public class CompanyDAO implements DataAccessObject {
    private long id;
    private String name;
    private Map<Long, String> projects;

    public CompanyDAO() {
    }

    public CompanyDAO(String name) {
        this.name = name;
    }

    public CompanyDAO(long id, String name) {
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
