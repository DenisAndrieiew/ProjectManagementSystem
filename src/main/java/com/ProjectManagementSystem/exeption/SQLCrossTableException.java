package com.ProjectManagementSystem.exeption;

public class SQLCrossTableException extends RuntimeException {
    @Override
    public String getMessage() {
        return "wrong convert types";
    }
}
