package com.ProjectManagmentSystem.dao.model;

public class CustomersDAO implements DataAccessObject{
    private long id;
    private String name;

    public CustomersDAO() {
    }

    public CustomersDAO(String name) {
        this.name = name;
    }

    public CustomersDAO(long id, String name) {
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
