package com.ProjectManagementSystem.controller.commands;

public interface Command {
    void execute(String[] args);
    String commandName();
    String commandDescription();
    default boolean canProcess(String command) {
        return commandName().equals(command);
    }
}
