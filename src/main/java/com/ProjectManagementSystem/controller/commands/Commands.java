package com.ProjectManagementSystem.controller.commands;

import com.ProjectManagementSystem.view.Console;

public enum Commands {
    HELP(new Help(Console.getInstance())),
    DEVS_BY_PROJECT(new DevsByProject(Console.getInstance())),
    SALARY_BY_PROJECT(new SalaryByProject(Console.getInstance())),
    DEVS_BY_BRUNCH(new DevsByBrunch(Console.getInstance()));

    private Command command;
    private Commands(Command command){
        this.command=command;
    }

    public Command getCommand() {
        return command;
    }
}
