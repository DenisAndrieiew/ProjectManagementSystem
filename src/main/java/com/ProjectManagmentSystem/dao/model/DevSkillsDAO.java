package com.ProjectManagmentSystem.dao.model;

public class DevSkillsDAO implements DataAccessObject{
    private long id;
    private long devId;
    private long skillId;
    private long skillLevel;

    public DevSkillsDAO() {
    }

    public DevSkillsDAO(int id, int devId, int skillId, int skillLevel) {
        this.id=id;
        this.devId = devId;
        this.skillId = skillId;
        this.skillLevel = skillLevel;
    }

    @Override
    public long getId() {
        return id;
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
