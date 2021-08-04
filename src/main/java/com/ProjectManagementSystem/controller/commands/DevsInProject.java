package com.ProjectManagementSystem.controller.commands;

import com.ProjectManagementSystem.dao.ProjectsRepository;
import com.ProjectManagementSystem.dto.ProjectsDTO;
import com.ProjectManagementSystem.jdbc.config.DatabaseConnectionManager;
import com.ProjectManagementSystem.view.Console;
import com.ProjectManagementSystem.view.View;

import java.util.Arrays;

public class DevsInProject implements Command{

private final View view;

    public DevsInProject(View view) {
        this.view = view;
    }

    @Override
    public void execute() {
        view.write("please input project name");
        String project = view.read();
        view.write(project);
        DatabaseConnectionManager connectionManager = new DatabaseConnectionManager("localhost",
                "homework_3", "postgres", "1234");
        ProjectsRepository projectsRepository = new ProjectsRepository(connectionManager);
        projectsRepository.
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
