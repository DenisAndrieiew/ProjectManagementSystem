package com.ProjectManagementSystem.repository.model;

public class BrunchDAO implements DataAccessObject{
    private long id;
    private String brunch;

    public BrunchDAO() {
    }

    public BrunchDAO(long id, String brunch) {
        this.id = id;
        this.brunch = brunch;
    }

    public BrunchDAO(String brunch) {
        this.brunch = brunch;
    }

    @Override
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrunch() {
        return brunch;
    }

    public void setBrunch(String brunch) {
        this.brunch = brunch;
    }
}
