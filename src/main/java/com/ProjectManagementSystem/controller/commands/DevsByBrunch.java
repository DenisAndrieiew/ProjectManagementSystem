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
        DatabaseConnectionManager connectionManager = DatabaseConnectionManager.getInstance();
        BrunchRepository brunchRepository = new BrunchRepository(connectionManager);
        BrunchDAO brunchDAO = brunchRepository.findByString("name", brunch.toString()).get(0);
        BrunchDTO brunchDTO = (BrunchDTO) brunchRepository.getConverter().toDTO(brunchDAO);
        DeveloperRepository developerRepository = new DeveloperRepository(connectionManager);
        DevSkillsRepository devSkillsRepository = new DevSkillsRepository(connectionManager);
        devSkillsRepository.findByNumber("skill_id", brunchDTO.getId()).stream()
                .map(devSkillsDAO -> (DevSkillsDTO) devSkillsRepository.getConverter().toDTO(devSkillsDAO))
                .map(devSkillsDTO -> (DeveloperDTO) developerRepository.getConverter().toDTO(
                        developerRepository.findById(devSkillsDTO.getDevId())))
                .forEach(developer -> view.write(developer.getFirstName() + " " + developer.getLastName()));

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
