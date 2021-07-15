package com.ProjectManagmentSystem.dto.enums;

public enum Sex {
    MALE {
        @Override
        public String toString() {
            return "male";
        }
    }, FEMALE {
        @Override
        public String toString() {
            return "female";
        }
    }
}
