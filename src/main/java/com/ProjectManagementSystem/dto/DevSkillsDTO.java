package com.ProjectManagementSystem.dto;

import com.ProjectManagementSystem.dto.enums.Brunch;
import com.ProjectManagementSystem.dto.enums.SkillLevel;

public class DevSkillsDTO implements DataTransferObject {
    private int id;
    private String brunch;
    private String level;
    private long developerId;

    public DevSkillsDTO() {
    }

    public long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(long developerId) {
        this.developerId = developerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrunch() {
        return brunch;
    }

    public void setBrunch(String brunch) {
        this.brunch = brunch;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}