package com.ProjectManagementSystem.service.converter;

import com.ProjectManagementSystem.dto.ProjectDTO;
import com.ProjectManagementSystem.model.dao.CompanyDAO;
import com.ProjectManagementSystem.model.dao.CustomerDAO;
import com.ProjectManagementSystem.model.dao.DeveloperDAO;
import com.ProjectManagementSystem.model.dao.ProjectDAO;
import com.ProjectManagementSystem.model.repositories.CompanyRepository;
import com.ProjectManagementSystem.model.repositories.CustomerRepository;
import com.ProjectManagementSystem.model.repositories.DeveloperRepository;
import com.ProjectManagementSystem.model.repositories.EntityRepository;

import java.util.*;

public class ProjectConverter implements Converter<ProjectDAO, ProjectDTO> {
    private static EntityRepository<CompanyDAO> companyRepository;
    private static EntityRepository<CustomerDAO> customersRepository;
    private static EntityRepository<DeveloperDAO> developerRepository;

    public ProjectConverter() {
        companyRepository = new CompanyRepository();
        customersRepository = new CustomerRepository();
        developerRepository = new DeveloperRepository();

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
        Set<DeveloperDAO> developers = new HashSet<>();
        if (Objects.nonNull(dto.getDeveloperIds())) {
            dto.getDeveloperIds().forEach(devId -> developers.add(developerRepository.findById(devId)));
        }
        dao.setDevelopers(developers);
        return dao;
    }

    @Override
    public ProjectDTO toDTO(ProjectDAO dao) {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(dao.getId());
        dto.setName(dao.getName());
        dto.setDescription(dao.getDescription());
        Set<Integer> devIds = new HashSet<>();
        dao.getDevelopers().forEach(developer -> devIds.add(developer.getId()));
        dto.setDeveloperIds(devIds);
        List<String> developers = new LinkedList<>();
        dao.getDevelopers().forEach(developer -> developers
                .add(developer.getFirstName() + " " + developer.getLastName()));
        dto.setDevelopers(developers);
        dto.setCompany(dao.getCompany().getName());
        dto.setCustomer(dao.getCustomer().getName());
        dto.setBeginDate(dao.getBegin_date());
        int cost = dao.getDevelopers().stream().mapToInt(DeveloperDAO::getSalary).sum();
        dto.setCost(cost);
        return dto;
    }

}
