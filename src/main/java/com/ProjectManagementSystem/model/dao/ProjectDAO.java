package com.ProjectManagementSystem.model.dao;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projects")
public class ProjectDAO implements DataAccessObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private CustomerDAO customer;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private CompanyDAO company;
    @Column(name = "description")
    private String description;
    @Column(name = "begin_date")
    private Instant begin_date;
    @ManyToMany(mappedBy = "projects", fetch = FetchType.EAGER)
    private Set<DeveloperDAO> developers = new HashSet<>();

    public ProjectDAO() {

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

    public CustomerDAO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDAO customer) {
        this.customer = customer;
    }

    public CompanyDAO getCompany() {
        return company;
    }

    public void setCompany(CompanyDAO company) {
        this.company = company;
    }

    public Set<DeveloperDAO> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<DeveloperDAO> developers) {
        this.developers = developers;
    }
}
