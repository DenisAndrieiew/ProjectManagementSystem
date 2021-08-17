package com.ProjectManagementSystem.model.dao;

import com.ProjectManagementSystem.dto.enums.Brunch;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "brunch")
public class BrunchDAO implements DataAccessObject{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private Brunch brunch;
    @OneToMany
    @JoinTable(name = "dev_skills", joinColumns = {@JoinColumn(name = "skill_id")})
    private Set<DevSkillsDAO> devSkills;


    public BrunchDAO() {
    }

    public Set<DevSkillsDAO> getDevSkills() {
        return devSkills;
    }

    public void setDevSkills(Set<DevSkillsDAO> devSkills) {
        this.devSkills = devSkills;
    }

    @Override
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BrunchDAO)) return false;
        BrunchDAO brunchDAO = (BrunchDAO) o;
        return id == brunchDAO.id && brunch == brunchDAO.brunch;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brunch);
    }
}
