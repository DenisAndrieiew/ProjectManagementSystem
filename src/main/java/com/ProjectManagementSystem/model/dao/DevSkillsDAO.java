package com.ProjectManagementSystem.model.dao;

import com.ProjectManagementSystem.dto.enums.Brunch;
import com.ProjectManagementSystem.dto.enums.SkillLevel;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "skills")
public class DevSkillsDAO implements DataAccessObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "brunch")
    @Enumerated(EnumType.STRING)
    private Brunch brunch;
    @Column(name = "stage")
    @Enumerated(EnumType.STRING)
    private SkillLevel level;

    @ManyToMany(mappedBy = "devSkills", fetch = FetchType.EAGER)
    private Set<DeveloperDAO> developers;

    public DevSkillsDAO() {
    }

    @Override
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public Brunch getBrunch() {
        return brunch;
    }

    public void setBrunch(Brunch brunch) {
        this.brunch = brunch;
    }

    public SkillLevel getLevel() {
        return level;
    }

    public void setLevel(SkillLevel level) {
        this.level = level;
    }

    public Set<DeveloperDAO> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<DeveloperDAO> developers) {
        this.developers = developers;
    }
}