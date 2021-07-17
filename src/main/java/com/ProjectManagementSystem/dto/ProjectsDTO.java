package com.ProjectManagementSystem.dto;

public class ProjectsDTO implements DataTransferObject{
    private long id;
    private String name;
    private long customerId;
    private long companyId;
    private String description;
    private int cost;

    public ProjectsDTO() {
    }

    public ProjectsDTO(String name, long customerId, long companyId, String description, int cost) {
        this.name = name;
        this.customerId = customerId;
        this.companyId = companyId;
        this.description = description;
        this.cost = cost;
    }

    public ProjectsDTO(long id, String name, long customerId, long companyId, String description, int cost) {
        this.id = id;
        this.name = name;
        this.customerId = customerId;
        this.companyId = companyId;
        this.description = description;
        this.cost = cost;
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
}
