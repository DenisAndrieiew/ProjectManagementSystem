package com.ProjectManagementSystem.dao.model;

public class DevSkillsDAO implements DataAccessObject {
    private long id;
    private long devId;
    private long skillId;
    private long skillLevel;

    public DevSkillsDAO() {
    }

    public DevSkillsDAO(long id, long devId, long skillId, long skillLevel) {
        this.id = id;
        this.devId = devId;
        this.skillId = skillId;
        this.skillLevel = skillLevel;
    }

    public DevSkillsDAO(long devId, long skillId, long skillLevel) {
        this.devId = devId;
        this.skillId = skillId;
        this.skillLevel = skillLevel;
    }


    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {this.id = id;}

    public long getDevId() {
        return devId;
    }

    public void setDevId(long devId) {
        this.devId = devId;
    }

    public long getSkillId() {
        return skillId;
    }

    public void setSkillId(long skillId) {
        this.skillId = skillId;
    }

    public long getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(long skillLevel) {
        this.skillLevel = skillLevel;
    }
}
