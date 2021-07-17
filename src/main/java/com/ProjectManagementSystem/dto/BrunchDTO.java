package com.ProjectManagementSystem.dto;

import com.ProjectManagementSystem.dto.enums.Brunch;

public class BrunchDTO implements DataTransferObject{
private long id;
    private Brunch brunch;

    public BrunchDTO() {
    }

    public BrunchDTO(Brunch brunch) {
        this.brunch = brunch;
    }

    public BrunchDTO(long id, Brunch brunch) {
        this.id = id;
        this.brunch = brunch;
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

    @Override
    public String toString() {
        return "BrunchDTO{" +
                "brunch=" + brunch +
                '}';
    }
}
