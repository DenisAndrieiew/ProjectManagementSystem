package com.ProjectManagmentSystem.dto;

public class DevSkillsDTO implements DataTransferObject {
    private long devId;
    private long skillId;
    private long skillLevel;

    public DevSkillsDTO(long devId, long skillId, long skillLevel) {
        this.devId = devId;
        this.skillId = skillId;
        this.skillLevel = skillLevel;

    }

    public static String getObjectName() {
        return "dev_skills";
    }

    public long getDevId() {
        return devId;
    }

    public void setDevId(int devId) {
        this.devId = devId;
    }

    public long getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public long getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }
}