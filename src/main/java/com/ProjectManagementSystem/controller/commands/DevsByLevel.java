package com.ProjectManagementSystem.controller.commands;

import com.ProjectManagementSystem.model.DevSkillsRepository;
import com.ProjectManagementSystem.model.DeveloperRepository;
import com.ProjectManagementSystem.model.SkillLevelRepository;
import com.ProjectManagementSystem.model.dao.SkillLevelDAO;
import com.ProjectManagementSystem.dto.DevSkillsDTO;
import com.ProjectManagementSystem.dto.DeveloperDTO;
import com.ProjectManagementSystem.dto.SkillLevelDTO;
import com.ProjectManagementSystem.dto.enums.SkillLevel;
import com.ProjectManagementSystem.view.View;

import java.util.Arrays;
import java.util.Objects;

public class DevsByLevel implements Command {
    private View view;

    public DevsByLevel(View view) {
        this.view = view;
    }

    @Override
    public void execute() {
        SkillLevel level = null;
        while (Objects.isNull(level)) {
            view.write("please input skill level, choose from list");
            Arrays.stream(SkillLevel.values()).map(level1 -> level1.toString()).forEach(view::writeL);
            view.write("\n");
            String levelName = view.read();
            level = Arrays.stream(SkillLevel.values()).filter(level1 -> level1.toString().equals(levelName))
                    .findAny().orElse(null);
        }
        SkillLevelRepository levelRepository = new SkillLevelRepository();
        SkillLevelDAO levelDAO = levelRepository.findByString("name", level.toString()).get(0);
        SkillLevelDTO levelDTO = (SkillLevelDTO) levelRepository.getConverter().toDTO(levelDAO);
        DeveloperRepository developerRepository = new DeveloperRepository();
        DevSkillsRepository devSkillsRepository = new DevSkillsRepository();

        devSkillsRepository.findByNumber("skill_level", levelDTO.getId()).stream()
                .map(devSkillsDAO -> (DevSkillsDTO) devSkillsRepository.getConverter().toDTO(devSkillsDAO))
                .map(devSkillsDTO -> (DeveloperDTO) developerRepository.getConverter().toDTO(
                        developerRepository.findById(devSkillsDTO.getDevId())))
                .forEach(developer -> view.write(developer.getFirstName() + " " + developer.getLastName()));

    }

    @Override
    public String commandName() {
        return "devs_by_level";
    }

    @Override
    public String commandDescription() {
        return "display list od developers by selected skill level";
    }
}
