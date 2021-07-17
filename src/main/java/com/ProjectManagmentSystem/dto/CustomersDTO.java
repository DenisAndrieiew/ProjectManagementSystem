package com.ProjectManagmentSystem.dto;

public class CustomersDTO implements DataTransferObject{
    private long id;
    private String name;

    public CustomersDTO(String name) {
        this.name = name;
    }

    public CustomersDTO(long id, String name) {
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
