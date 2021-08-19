package com.ProjectManagementSystem.model.dao;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "company")
public class CompanyDAO implements DataAccessObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    private Set<ProjectDAO> projects;

    public CompanyDAO() {
    }


    public Set<ProjectDAO> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectDAO> projects) {
        this.projects = projects;
    }

    @Override
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
