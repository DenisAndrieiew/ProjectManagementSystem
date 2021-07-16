package com.ProjectManagmentSystem.dto;

import com.ProjectManagmentSystem.dto.enums.SkillLevel;

public class SkillLevelDTO implements DataTransferObject{
    private long id;
    private SkillLevel level;

    public SkillLevelDTO(SkillLevel level) {
        this.level = level;
    }

    public SkillLevelDTO(long id, SkillLevel level) {
        this.id = id;
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SkillLevel getLevel() {
        return level;
    }

    public void setLevel(SkillLevel level) {
        this.level = level;
    }
    public static String getObjectName(){
        return "skill_level";
    }
}
