package com.ProjectManagementSystem.controller.commands;

import com.ProjectManagementSystem.view.View;

import java.util.Arrays;

public class Help implements Command{
    private final View view;

    public Help(View view) {
        this.view = view;
    }

    @Override
    public void execute(String[] args) {
        Arrays.stream(Commands.values()).forEach(command -> {
            view.write(command.getCommand().commandName() +" - "+ command.getCommand().commandDescription() );
        });
        view.write("exit - to exit application");
    }

    @Override
    public String commandName() {
        return "help";
    }

    @Override
    public String commandDescription() {
        return "show a list of commands";
    }
}
