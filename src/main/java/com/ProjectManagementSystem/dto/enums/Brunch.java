package com.ProjectManagementSystem.dto.enums;

import java.util.Arrays;

public enum Brunch {
    JAVA{
        @Override
    public String toString() {
        return "Java";
    }
    }, C_SHARP {
        @Override
        public String toString() {
            return "C#";
        }},
    JAVA_SCRIPT{
        @Override
        public String toString() {
            return "JS";
        }},
    CPP{
        @Override
        public String toString() {
            return "C++";
        }},
    PHP{
        @Override
        public String toString() {
            return "PHP";
        }};
    public static Brunch toBrunch (String value){
        return Arrays.stream(Brunch.values()).filter(brunch->brunch.toString().equals(value)).findAny().orElse(null);
    }
}
