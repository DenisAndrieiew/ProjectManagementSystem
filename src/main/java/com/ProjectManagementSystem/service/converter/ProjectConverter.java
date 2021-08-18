package com.ProjectManagementSystem.service.converter;

import com.ProjectManagementSystem.dto.DeveloperDTO;
import com.ProjectManagementSystem.dto.ProjectDTO;
import com.ProjectManagementSystem.model.dao.CompanyDAO;
import com.ProjectManagementSystem.model.dao.CustomerDAO;
import com.ProjectManagementSystem.model.dao.DeveloperDAO;
import com.ProjectManagementSystem.model.dao.ProjectDAO;
import com.ProjectManagementSystem.model.repositories.EntityRepository;
import com.ProjectManagementSystem.model.repositories.GenericEntityRepository;

public class ProjectConverter implements Converter<ProjectDAO, ProjectDTO> {
    private static EntityRepository<CompanyDAO> companyRepository;
    private static EntityRepository<CustomerDAO> customersRepository;
    private static Converter<DeveloperDAO, DeveloperDTO> developerConverter;

    public ProjectConverter() {
        companyRepository = new GenericEntityRepository<>(CompanyDAO.class);
        customersRepository = new GenericEntityRepository<>(CustomerDAO.class);
        developerConverter = new DeveloperConverter();
    }

    @Override
    public ProjectDAO fromDTO(ProjectDTO dto) {
        ProjectDAO dao = new ProjectDAO();
        dao.setId(dto.getId());
        dao.setName(dto.getName());
        dao.setDescription(dao.getDescription());
        dao.setBegin_date(dto.getBeginDate());
        dao.setCompany(companyRepository.findByUniqueName("name", dto.getCompany()));
        dao.setCustomer(customersRepository.findByUniqueName("name", dto.getCustomer()));
        dao.setDevelopers(developerConverter.fromDTOSet(dto.getDevelopers()));
        return dao;
    }

    @Override
    public ProjectDTO toDTO(ProjectDAO dao) {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(dao.getId());
        dto.setName(dao.getName());
        dto.setDescription(dao.getDescription());
        dto.setDevelopers(developerConverter.toDTOSet(dao.getDevelopers()));
        dto.setCompany(dao.getCompany().getName());
        dto.setCustomer(dao.getCustomer().getName());
        dto.setBeginDate(dao.getBegin_date());
        dto.setCost(dao.getCost());
        return dto;
    }

}
