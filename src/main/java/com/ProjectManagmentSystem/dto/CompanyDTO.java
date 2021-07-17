package com.ProjectManagmentSystem.dto;

public class CompanyDTO implements DataTransferObject{
    private long id;
    private String name;

    public CompanyDTO(String name) {
        this.name = name;
    }

    public CompanyDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

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