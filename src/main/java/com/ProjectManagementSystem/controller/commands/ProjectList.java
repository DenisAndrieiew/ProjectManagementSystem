package com.ProjectManagementSystem.controller.commands;

import com.ProjectManagementSystem.dao.DevelopersInProjectsRepository;
import com.ProjectManagementSystem.dao.ProjectsRepository;
import com.ProjectManagementSystem.dto.ProjectsDTO;
import com.ProjectManagementSystem.jdbc.config.DatabaseConnectionManager;
import com.ProjectManagementSystem.view.View;

import java.util.Comparator;

public class ProjectList implements Command{
    private View view;

    public ProjectList(View view) {
        this.view = view;


    }

    @Override
    public void execute() {
        DatabaseConnectionManager connectionManager = DatabaseConnectionManager.getInstance();
        ProjectsRepository projectsRepository = new ProjectsRepository(connectionManager);
        DevelopersInProjectsRepository developers = new DevelopersInProjectsRepository(connectionManager);
        projectsRepository.findAll().stream().map(project->(ProjectsDTO) projectsRepository.getConverter().toDTO(project))
                .sorted(Comparator.comparing(ProjectsDTO::getBeginDate))
                .forEach(project->{
                    view.writeL(project.getBeginDate().toString());
                    view.writeL(project.getName());
                    view.write(String.valueOf(developers.findByNumber("project_id", project.getId()).size()));
                });
    }

    @Override
    public String commandName() {
        return "project_list";
    }

    @Override
    public String commandDescription() {
        return "show list of all projects";
    }
}
