package com.ProjectManagementSystem.dto;

import com.ProjectManagementSystem.dto.enums.Sex;

import java.util.HashSet;
import java.util.Set;

public class DeveloperDTO implements DataTransferObject {
    private long id;
    private String firstName;
    private String lastName;
    private int age;
    private Sex sex;
    private String comments;
    private int salary;
    private Set<DevSkillsDTO> devSkills = new HashSet<>();
    private Set<ProjectsDTO> projects = new HashSet<>();


    public DeveloperDTO() {
    }

    public DeveloperDTO(long id, String firstName, String lastName, int age, Sex sex, String comments, int salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
        this.comments = comments;
        this.salary = salary;
    }

    public DeveloperDTO(String firstName, String lastName, int age, Sex sex, String comments, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
        this.comments = comments;
        this.salary = salary;
    }

    public Set<DevSkillsDTO> getDevSkills() {
        return devSkills;
    }

    public void setDevSkills(Set<DevSkillsDTO> devSkills) {
        this.devSkills = devSkills;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Set<ProjectsDTO> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectsDTO> projects) {
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
