package com.ProjectManagementSystem.view;

import java.util.Scanner;

public class Console implements View{
    private Scanner scanner;
    private static volatile View instance = new Console();

    private Console() {
        scanner = new Scanner(System.in);
    }


    @Override
    public String read() {
        return scanner.nextLine();
    }

    @Override
    public void write(String message) {
        System.out.println(message);
    }


    public static View getInstance(){
        View result = instance;
        if (result != null) {
            return result;
        }
        synchronized(View.class) {
            if (instance == null) {
                instance = new Console();
            }
            return instance;
        }
    }
}
