package com.ProjectManagementSystem.dto;

import java.time.Instant;
import java.util.List;

public class ProjectsDTO implements DataTransferObject {
    private long id;
    private String name;
    private String customer;
    private String company;
    private String description;
    private int cost;
    private Instant beginDate;
    private List<String> developers;

    public ProjectsDTO() {
    }

    public ProjectsDTO(String name, String customer, String company,
                       String description, int cost, Instant beginDate) {
        this.name = name;
        this.customer = customer;
        this.company = company;
        this.description = description;
        this.cost = cost;
        this.beginDate = beginDate;
    }

    public ProjectsDTO(long id, String name, String customer, String company,
                       String description, int cost, Instant beginDate) {
        this.id = id;
        this.name = name;
        this.customer = customer;
        this.company = company;
        this.description = description;
        this.cost = cost;
        this.beginDate = beginDate;
    }

    public List<String> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<String> developers) {
        this.developers = developers;
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

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customerId) {
        this.customer = customerId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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
}
