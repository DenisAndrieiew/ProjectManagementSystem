package com.ProjectManagmentSystem.dao.model;

public class DevSkillsDAO{
    private int devId;
    private int skillId;
    private int skillLevel;

    public DevSkillsDAO() {
    }

    public DevSkillsDAO(int devId, int skillId, int skillLevel) {
        this.devId = devId;
        this.skillId = skillId;
        this.skillLevel = skillLevel;
    }

    public int getDevId() {
        return devId;
    }

    public void setDevId(int devId) {
        this.devId = devId;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }
}
