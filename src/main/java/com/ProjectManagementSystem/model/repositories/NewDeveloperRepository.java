package com.ProjectManagementSystem.model.repositories;

import com.ProjectManagementSystem.model.dao.DeveloperDAO;
import org.hibernate.SessionFactory;

public class NewDeveloperRepository extends GenericEntityRepository<DeveloperDAO> {


    public NewDeveloperRepository(SessionFactory sessionFactory, Class<DeveloperDAO> entityClass) {
        super(sessionFactory, DeveloperDAO.class);
    }

}
