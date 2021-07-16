package com.ProjectManagmentSystem.exeption;

public class WrongEnumException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Wrong value of enum type";
    }
}
