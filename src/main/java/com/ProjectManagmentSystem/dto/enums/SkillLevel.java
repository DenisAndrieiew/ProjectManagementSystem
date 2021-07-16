package com.ProjectManagmentSystem.dto.enums;

import java.util.Arrays;
import java.util.Optional;

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

    public static Optional<SkillLevel> toSkillLevel(String value) {
        return Arrays.stream(SkillLevel.values()).filter(level -> level.toString().equals(value)).findAny();
    }
}
