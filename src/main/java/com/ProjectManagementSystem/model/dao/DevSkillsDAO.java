package com.ProjectManagementSystem.model.dao;

import javax.persistence.*;

@Entity
@Table(name = "dev_skills")
public class DevSkillsDAO implements DataAccessObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "skill_id")
    private BrunchDAO brunch;
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "skill_level")
    private SkillLevelDAO skillLevel;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dev_id")
    private DeveloperDAO developer;


    public DevSkillsDAO() {
    }

    @Override
    public int getId() {
        return id;
    }

    public DeveloperDAO getDeveloper() {
        return developer;
    }

    public void setDeveloper(DeveloperDAO developer) {
        this.developer = developer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BrunchDAO getBrunch() {
        return brunch;
    }

    public void setBrunch(BrunchDAO brunch) {
        this.brunch = brunch;
    }

    public SkillLevelDAO getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(SkillLevelDAO skillLevel) {
        this.skillLevel = skillLevel;
    }
}