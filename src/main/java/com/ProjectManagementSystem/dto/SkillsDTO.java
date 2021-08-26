package com.ProjectManagementSystem.dto;

import java.util.Objects;

public class SkillsDTO implements DataTransferObject {
    private int id;
    private String brunch;
    private String level;

    public SkillsDTO() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SkillsDTO)) return false;
        SkillsDTO that = (SkillsDTO) o;
        return getBrunch().equals(that.getBrunch());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBrunch());
    }
}