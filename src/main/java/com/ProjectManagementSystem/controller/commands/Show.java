package com.ProjectManagementSystem.controller.commands;

import com.ProjectManagementSystem.view.Console;
import com.ProjectManagementSystem.view.View;

import java.util.Arrays;

public class Show implements Command{
    private final String H_MESSAGE = "please use key: -h to see available keys";

private final View view;

    public Show(View view) {
        this.view = view;
    }

    @Override
    public void execute(String[] args) {
if (args == null){view.write(H_MESSAGE);
    }
        Arrays.stream(args).forEach(arg->view.write(arg));

        }

    @Override
    public String commandName() {
        return "show";
    }

    @Override
    public String commandDescription() {
        return "show entities";
    }
}
