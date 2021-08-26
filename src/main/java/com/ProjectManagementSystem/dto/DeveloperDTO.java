package com.ProjectManagementSystem.dto;

import com.ProjectManagementSystem.dto.enums.Sex;

import java.util.HashSet;
import java.util.Set;

public class DeveloperDTO implements DataTransferObject {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private Sex sex;
    private String comments;
    private int salary;
    private Set<SkillsDTO> skills = new HashSet<>();
    private Set<ProjectDTO> projects = new HashSet<>();


    public DeveloperDTO() {
    }


    public Set<SkillsDTO> getSkills() {
        return skills;
    }

    public void setSkills(Set<SkillsDTO> skills) {
        this.skills = skills;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Set<ProjectDTO> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectDTO> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "DeveloperDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", comments='" + comments + '\'' +
                ", salary=" + salary +
                '}';
    }


}
