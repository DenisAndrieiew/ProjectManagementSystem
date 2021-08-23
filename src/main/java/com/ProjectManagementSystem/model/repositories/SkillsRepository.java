package com.ProjectManagementSystem.model.repositories;

import com.ProjectManagementSystem.model.dao.DevSkillsDAO;

public interface SkillsRepository extends EntityRepository<DevSkillsDAO>{
    public DevSkillsDAO findByBrunchAndLevel(String brunch, String level);
}
