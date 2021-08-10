package com.ProjectManagementSystem.dto;

import com.ProjectManagementSystem.dto.enums.Sex;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DeveloperDTO implements DataTransferObject {
    private long id;
    private String firstName;
    private String lastName;
    private int age;
    private Sex sex;
    private String comments;
    private int salary;
    private Map<String, String> skillLevels = new HashMap<String, String>();
    private List<String> projects = new LinkedList<String>();

    public Map<String, String> getSkillLevels() {
        return skillLevels;
    }

    public void setSkillLevels(Map<String, String> skillLevels) {
        this.skillLevels = skillLevels;
    }

    public List<String> getProjects() {
        return projects;
    }

    public void setProjects(List<String> projects) {
        this.projects = projects;
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
