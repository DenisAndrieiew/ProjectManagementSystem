package com.ProjectManagementSystem.dao.model;

public class CompanyDAO implements DataAccessObject{
    private long id;
    private String name;

    public CompanyDAO() {
    }

    public CompanyDAO(String name) {
        this.name = name;
    }

    public CompanyDAO(long id, String name) {
        this.id = id;
        this.name = name;
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
