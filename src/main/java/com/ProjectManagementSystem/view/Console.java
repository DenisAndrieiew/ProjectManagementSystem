package com.ProjectManagementSystem.view;

import java.util.Scanner;

public class Console implements View {
    private static volatile View instance = new Console();
    private Scanner scanner;

    private Console() {
        scanner = new Scanner(System.in);
    }

    public static View getInstance() {
        View result = instance;
        if (result != null) {
            return result;
        }
        synchronized (View.class) {
            if (instance == null) {
                instance = new Console();
            }
            return instance;
        }
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }

    @Override
    public void write(String message) {
        System.out.println(message);
    }

    @Override
    public void writeL(String message) {
        System.out.print(message + "\t");
    }
}
