package com.ProjectManagementSystem.dto;

import com.ProjectManagementSystem.dto.enums.Brunch;
import com.ProjectManagementSystem.dto.enums.SkillLevel;

public class DevSkillsDTO implements DataTransferObject {
    private long id;
    private Brunch brunch;
    private SkillLevel level;
    private long developerId;

    public DevSkillsDTO() {
    }

    public long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(long developerId) {
        this.developerId = developerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Brunch getBrunch() {
        return brunch;
    }

    public void setBrunch(Brunch brunch) {
        this.brunch = brunch;
    }

    public SkillLevel getLevel() {
        return level;
    }

    public void setLevel(SkillLevel level) {
        this.level = level;
    }
}