package com.ProjectManagementSystem.controller.commands;

import com.ProjectManagementSystem.dao.BrunchRepository;
import com.ProjectManagementSystem.dao.DevSkillsRepository;
import com.ProjectManagementSystem.dao.DeveloperRepository;
import com.ProjectManagementSystem.dao.model.BrunchDAO;
import com.ProjectManagementSystem.dto.BrunchDTO;
import com.ProjectManagementSystem.dto.DevSkillsDTO;
import com.ProjectManagementSystem.dto.DeveloperDTO;
import com.ProjectManagementSystem.dto.enums.Brunch;
import com.ProjectManagementSystem.jdbc.config.DatabaseConnectionManager;
import com.ProjectManagementSystem.view.View;

import java.util.Arrays;
import java.util.Objects;

public class DevsByBrunch implements Command {
    private View view;

    public DevsByBrunch(View view) {

        this.view = view;
    }

    @Override
    public void execute() {
        Brunch brunch = null;
        while (Objects.isNull(brunch)) {
            view.write("please input brunch name, choose from list");
            Arrays.stream(Brunch.values()).map(brunch1 -> brunch1.toString()).forEach(view::writeL);
            view.write("\n");
            String brunchName = view.read();
            brunch = Arrays.stream(Brunch.values()).filter(brunch1 -> brunch1.toString().equals(brunchName))
                    .findAny().orElse(null);
        }
        DatabaseConnectionManager connectionManager = new DatabaseConnectionManager("localhost",
                "homework_3", "postgres", "1234");
view.write(brunch.name()+" "+brunch.toString());
        BrunchRepository brunchRepository = new BrunchRepository(connectionManager);
        BrunchDAO brunchDAO = brunchRepository.findByString("name", brunch.toString()).get(0);
        view.write(brunchDAO.getBrunch());
        BrunchDTO brunchDTO = (BrunchDTO) brunchRepository.getConverter().toDTO(brunchDAO);
        DeveloperRepository developerRepository = new DeveloperRepository(connectionManager);
        DevSkillsRepository devSkillsRepository = new DevSkillsRepository(connectionManager);

        view.write("DTO: "+brunchDTO);
        devSkillsRepository.findByNumber("skill_id", brunchDTO.getId()).stream()
                .map(devSkillsDAO -> (DevSkillsDTO) devSkillsRepository.getConverter().toDTO(devSkillsDAO))
                .map(devSkillsDTO -> (DeveloperDTO) developerRepository.getConverter().toDTO(
                        developerRepository.findById(devSkillsDTO.getDevId())))
                .forEach(developer -> view.write(developer.getFirstName() + " " + developer.getLastName()));


        //        ProjectsRepository projectsRepository = new ProjectsRepository(connectionManager);
//        List<ProjectsDAO> projectsDAO = projectsRepository.findByString("name", projectName);
//        if (projectsDAO.size() > 0) {
//            ProjectsDTO project = (ProjectsDTO) projectsRepository.getConverter().toDTO(projectsDAO.get(0));
//            DevelopersInProjectsRepository devsInProjectsRepository = new DevelopersInProjectsRepository(connectionManager);
//            DeveloperRepository developerRepository = new DeveloperRepository(connectionManager);
//            devsInProjectsRepository.findByNumber("project_id", project.getId()).stream()
//                    .map(entity -> devsInProjectsRepository.getConverter().toDTO(entity))
//                    .map(entity -> (DevelopersInProjectsDTO) entity)
//                    .map(devsInProject1 -> (DeveloperDTO) developerRepository.getConverter().toDTO(
//                            developerRepository.findById(devsInProject1.getDevId())))
//                    .forEach(developerDTO -> view.write(developerDTO.getFirstName() + " " + developerDTO.getLastName()));
//        } else {
//            view.write("Nothing found. Make sure to enter the correct name for the project.");
//        }
    }

    @Override
    public String commandName() {
        return "devs_by_brunch";
    }

    @Override
    public String commandDescription() {
        return "displays list of developers skilled in some brunch";
    }
}
