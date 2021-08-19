package com.ProjectManagementSystem.dto;

import java.time.Instant;
import java.util.Set;

public class ProjectDTO implements DataTransferObject {
    private int id;
    private String name;
    private String customer;
    private String company;
    private String description;
    private int cost;
    private Instant beginDate;
    private Set<Integer> developerIds;

    public ProjectDTO() {
    }


    public Set<Integer> getDeveloperIds() {
        return developerIds;
    }

    public void setDeveloperIds(Set<Integer> developerIds) {
        this.developerIds = developerIds;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Instant getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Instant beginDate) {
        this.beginDate = beginDate;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
