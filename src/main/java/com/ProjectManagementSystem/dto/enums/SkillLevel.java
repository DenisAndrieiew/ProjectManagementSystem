package com.ProjectManagementSystem.dto.enums;

import java.util.Arrays;

public enum SkillLevel {
    JUNIOR {
        @Override
        public String toString() {
            return "Junior";
        }
    },
    MIDDLE {
        @Override
        public String toString() {
            return "Middle";
        }
    },
    SENIOR {
        @Override
        public String toString() {
            return "Senior";
        }
    };

    public static SkillLevel toSkillLevel(String value) {
        return Arrays.stream(SkillLevel.values()).filter(level -> level.toString().equals(value)).findAny().orElse(null);
    }
}
