package com.ProjectManagementSystem.dto;

import java.util.Map;

public class CustomersDTO implements DataTransferObject {
    private long id;
    private String name;
    private Map<Long, String> projects;

    public CustomersDTO(String name) {
        this.name = name;
    }

    public CustomersDTO(long id, String name) {
        this.id = id;
        this.name = name;

    }

    public CustomersDTO() {
    }

    public Map<Long, String> getProjects() {
        return projects;
    }

    public void setProjects(Map<Long, String> projects) {
        this.projects = projects;
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
