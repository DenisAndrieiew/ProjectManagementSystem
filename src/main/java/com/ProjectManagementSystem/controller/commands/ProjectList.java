package com.ProjectManagementSystem.controller.commands;

import com.ProjectManagementSystem.repository.DevelopersInProjectsRepository;
import com.ProjectManagementSystem.repository.ProjectsRepository;
import com.ProjectManagementSystem.dto.ProjectsDTO;
import com.ProjectManagementSystem.view.View;

import java.util.Comparator;

public class ProjectList implements Command {
    private View view;

    public ProjectList(View view) {
        this.view = view;


    }

    @Override
    public void execute() {
        ProjectsRepository projectsRepository = new ProjectsRepository();
        DevelopersInProjectsRepository developers = new DevelopersInProjectsRepository();
        projectsRepository.findAll().stream().
                map(project -> (ProjectsDTO) projectsRepository.getConverter().toDTO(project))
                .sorted(Comparator.comparing(ProjectsDTO::getBeginDate))
                .forEach(project -> {
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
