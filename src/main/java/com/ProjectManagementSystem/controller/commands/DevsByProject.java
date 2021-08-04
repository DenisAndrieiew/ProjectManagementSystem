package com.ProjectManagementSystem.controller.commands;

import com.ProjectManagementSystem.dao.DeveloperRepository;
import com.ProjectManagementSystem.dao.DevelopersInProjectsRepository;
import com.ProjectManagementSystem.dao.ProjectsRepository;
import com.ProjectManagementSystem.dao.model.ProjectsDAO;
import com.ProjectManagementSystem.dto.DeveloperDTO;
import com.ProjectManagementSystem.dto.DevelopersInProjectsDTO;
import com.ProjectManagementSystem.dto.ProjectsDTO;
import com.ProjectManagementSystem.jdbc.config.DatabaseConnectionManager;
import com.ProjectManagementSystem.view.View;

import java.util.List;

public class DevsByProject implements Command {

    private final View view;

    public DevsByProject(View view) {
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
            DevelopersInProjectsRepository devsInProjectsRepository = new DevelopersInProjectsRepository(connectionManager);
            DeveloperRepository developerRepository = new DeveloperRepository(connectionManager);
            devsInProjectsRepository.findByNumber("project_id", project.getId()).stream()
                    .map(entity -> devsInProjectsRepository.getConverter().toDTO(entity))
                    .map(entity -> (DevelopersInProjectsDTO) entity)
                    .map(devsInProject1 -> (DeveloperDTO) developerRepository.getConverter().toDTO(
                            developerRepository.findById(devsInProject1.getDevId())))
                    .forEach(developerDTO -> view.write(developerDTO.getFirstName() + " " + developerDTO.getLastName()));
        } else {
            view.write("Nothing found. Make sure to enter the correct name for the project.");
        }
    }

    @Override
    public String commandName() {
        return "devs_in_project";
    }

    @Override
    public String commandDescription() {
        return "show developers in separate project";
    }
}
