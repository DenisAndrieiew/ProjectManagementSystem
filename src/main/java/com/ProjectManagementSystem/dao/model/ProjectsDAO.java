package com.ProjectManagementSystem.dao.model;

import java.time.Instant;
import java.time.LocalDate;

public class ProjectsDAO implements DataAccessObject {
    private long id;
    private String name;
    private long customerId;
    private long companyId;
    private String description;
    private int cost;
    private Instant begin_date;

    public ProjectsDAO() {
    }

    public ProjectsDAO(String name, long customerId, long companyId, String description, int cost, Instant begin_date) {
        this.name = name;
        this.customerId = customerId;
        this.companyId = companyId;
        this.description = description;
        this.cost = cost;
        this.begin_date = begin_date;
    }

    public ProjectsDAO(long id, String name, long customerId, long companyId, String description, int cost, Instant begin_date) {
        this.id = id;
        this.name = name;
        this.customerId = customerId;
        this.companyId = companyId;
        this.description = description;
        this.cost = cost;
        this.begin_date = begin_date;
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Instant getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(Instant begin_date) {
        this.begin_date = begin_date;
    }
}
