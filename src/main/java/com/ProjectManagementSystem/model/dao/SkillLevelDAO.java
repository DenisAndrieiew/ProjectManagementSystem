package com.ProjectManagementSystem.model.dao;

import com.ProjectManagementSystem.dto.enums.SkillLevel;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "skill_level")
public class SkillLevelDAO implements DataAccessObject{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private SkillLevel level;
    @OneToMany(mappedBy = "brunch", fetch = FetchType.EAGER)
    private Set<DevSkillsDAO> devSkills;

    public SkillLevelDAO() {
    }

    public Set<DevSkillsDAO> getDevSkills() {
        return devSkills;
    }

    public void setDevSkills(Set<DevSkillsDAO> devSkills) {
        this.devSkills = devSkills;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SkillLevel getLevel() {
        return level;
    }

    public void setLevel(SkillLevel level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SkillLevelDAO)) return false;
        SkillLevelDAO that = (SkillLevelDAO) o;
        return id == that.id && level == that.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, level);
    }
}
