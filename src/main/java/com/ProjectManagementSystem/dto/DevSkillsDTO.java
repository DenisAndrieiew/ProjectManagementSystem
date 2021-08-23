package com.ProjectManagementSystem.dto;

import java.util.Objects;
import java.util.Set;

public class DevSkillsDTO implements DataTransferObject {
    private int id;
    private String brunch;
    private String level;

    public DevSkillsDTO() {
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
        if (!(o instanceof DevSkillsDTO)) return false;
        DevSkillsDTO that = (DevSkillsDTO) o;
        return getBrunch().equals(that.getBrunch());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBrunch());
    }
}