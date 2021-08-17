package com.ProjectManagementSystem.model.dao;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projects")
public class ProjectsDAO implements DataAccessObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "customer_id")
    private long customerId;
    @Column(name = "company_id")
    private long companyId;
    @Column(name = "description")
    private String description;
    //    @Column(name = "id")
//    private int cost;
    @Column(name = "begin_date")
    private Instant begin_date;
    @ManyToMany(mappedBy = "projects")
    private Set<DeveloperDAO> developers = new HashSet<>();

    public ProjectsDAO() {
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

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(Instant begin_date) {
        this.begin_date = begin_date;
    }
}
