package com.ProjectManagementSystem.dto;

import java.util.Map;
import java.util.Set;

public class CompanyDTO implements DataTransferObject {
    private long id;
    private String name;
    private Set<ProjectsDTO> projects;


    public CompanyDTO() {
    }

    public Set<ProjectsDTO> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectsDTO> projects) {
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
