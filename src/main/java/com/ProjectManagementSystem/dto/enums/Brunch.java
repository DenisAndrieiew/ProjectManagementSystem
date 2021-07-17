package com.ProjectManagementSystem.dto.enums;

import java.util.Arrays;
import java.util.Optional;

public enum Brunch {
    JAVA{
        @Override
    public String toString() {
        return "Java";
    }
    }, CSharp {
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
    public static Optional<Brunch> toBrunch (String value){
        return Arrays.stream(Brunch.values()).filter(brunch->brunch.toString().equals(value)).findAny();
    }
}
