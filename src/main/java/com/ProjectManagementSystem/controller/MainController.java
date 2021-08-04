package com.ProjectManagementSystem.controller;

import com.ProjectManagementSystem.controller.commands.Command;
import com.ProjectManagementSystem.controller.commands.Commands;
import com.ProjectManagementSystem.view.Console;
import com.ProjectManagementSystem.view.View;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainController {
    private View view;
    private List<Command> commandList;

    public MainController() {
        this.view = Console.getInstance();
        commandList = Arrays.stream(Commands.values()).map(Commands::getCommand).collect(Collectors.toList());
    }

    public void run() {
        view.write("Welcome to ProjectManagementSystem.");
        doCommand();
    }

    private void doCommand() {
        boolean isNotExit = true;
        while (isNotExit) {
            view.write("Please enter a command or help to retrieve command list");
            String inputCommand = view.read().toLowerCase();
            if (inputCommand.equals("exit")) {
                break;
            }
            for (Command command : commandList) {
                if (command.canProcess(inputCommand)) {
                    command.execute();
                    break;
                }

            }
        }
    }
}