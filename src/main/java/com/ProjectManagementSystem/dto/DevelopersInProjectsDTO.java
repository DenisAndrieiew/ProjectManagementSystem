package com.ProjectManagementSystem.dto;

public class DevelopersInProjectsDTO implements DataTransferObject{
    private long id;
    private long devId;
    private long projectId;

    public DevelopersInProjectsDTO(long devId, long projectId) {
        this.devId = devId;
        this.projectId = projectId;
    }

    public DevelopersInProjectsDTO(long id, long devId, long projectId) {
        this.id = id;
        this.devId = devId;
        this.projectId = projectId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDevId() {
        return devId;
    }

    public void setDevId(long devId) {
        this.devId = devId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }
}
