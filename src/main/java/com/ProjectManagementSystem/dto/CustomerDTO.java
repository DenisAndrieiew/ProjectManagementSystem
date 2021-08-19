package com.ProjectManagementSystem.dto;

import java.util.Set;

public class CustomerDTO implements DataTransferObject {
    private int id;
    private String name;
    private Set<ProjectDTO> projects;


    public CustomerDTO() {
    }

    public Set<ProjectDTO> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectDTO> projects) {
        this.projects = projects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
