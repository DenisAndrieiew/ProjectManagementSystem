package com.ProjectManagementSystem.model.repositories;

import com.ProjectManagementSystem.model.dao.SkillsDAO;

public interface SkillsRepositoryInterface extends EntityRepository<SkillsDAO>{
    public SkillsDAO findByBrunchAndLevel(String brunch, String level);
}
