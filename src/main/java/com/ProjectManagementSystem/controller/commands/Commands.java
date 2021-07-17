package com.ProjectManagementSystem.controller.commands;

import com.ProjectManagementSystem.view.Console;

public enum Commands {
    HELP(new Help(Console.getInstance())),
    SHOW(new Show(Console.getInstance()));

    private Command command;
    private Commands(Command command){
        this.command=command;
    }

    public Command getCommand() {
        return command;
    }
}
