package com.ProjectManagementSystem.controller.commands;

import com.ProjectManagementSystem.dao.ProjectsRepository;
import com.ProjectManagementSystem.dao.model.ProjectsDAO;
import com.ProjectManagementSystem.dto.ProjectsDTO;
import com.ProjectManagementSystem.jdbc.config.DatabaseConnectionManager;
import com.ProjectManagementSystem.view.View;

import java.util.List;

public class SalaryByProject implements Command {

    private final View view;

    public SalaryByProject(View view) {
        this.view = view;
    }

    @Override
    public void execute() {
        view.write("please input project name");
        String projectName = view.read();
        DatabaseConnectionManager connectionManager = DatabaseConnectionManager.getInstance();
        ProjectsRepository projectsRepository = new ProjectsRepository(connectionManager);
        List<ProjectsDAO> projectsDAO = projectsRepository.findByString("name", projectName);
        if (projectsDAO.size() > 0) {
         ProjectsDTO project = (ProjectsDTO) projectsRepository.getConverter().toDTO(projectsDAO.get(0));
         view.write("Total salary is - "+ project.getCost());
        } else {
            view.write("Nothing found. Make sure to enter the correct name for the project.");
        }
    }

    @Override
    public String commandName() {
        return "salary_by_project";
    }

    @Override
    public String commandDescription() {
        return "displays the salary of all project developers";
    }
}
