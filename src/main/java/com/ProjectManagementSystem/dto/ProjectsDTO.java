package com.ProjectManagementSystem.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class ProjectsDTO implements DataTransferObject {
    private long id;
    private String name;
    private long customerId;
    private long companyId;
    private String description;
    private int cost;
    private Instant beginDate;

    public ProjectsDTO() {
    }

    public ProjectsDTO(String name, long customerId, long companyId, String description, int cost, Instant beginDate) {
        this.name = name;
        this.customerId = customerId;
        this.companyId = companyId;
        this.description = description;
        this.cost = cost;
        this.beginDate = beginDate;
    }

    public ProjectsDTO(long id, String name, long customerId, long companyId, String description, int cost, Instant beginDate) {
        this.id = id;
        this.name = name;
        this.customerId = customerId;
        this.companyId = companyId;
        this.description = description;
        this.cost = cost;
        this.beginDate = beginDate;
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Instant getBeginDate() {return beginDate;}

    public void setBeginDate(Instant beginDate) {this.beginDate = beginDate;}
}
