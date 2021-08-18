package com.ProjectManagementSystem.model.dao;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customers")
public class CustomerDAO implements DataAccessObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "projects")
    private Set<ProjectDAO> projects;

    public CustomerDAO() {
    }


    public Set<ProjectDAO> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectDAO> projects) {
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
