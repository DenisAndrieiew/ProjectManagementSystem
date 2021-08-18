package com.ProjectManagementSystem.model.dao;

import com.ProjectManagementSystem.dto.enums.Sex;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "developers")
//@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "Developer")
public class DeveloperDAO implements DataAccessObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "age")
    private int age;
    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @Column(name = "comments")
    private String comments;
    @Column(name = "salary")
    private int salary;
    @ManyToMany()
    @JoinTable(name = "devs_in_project", joinColumns = {@JoinColumn(name = "dev_id")}
            , inverseJoinColumns = {@JoinColumn(name = "project_id")})
    private Set<ProjectDAO> projects;
    @OneToMany
    private Set<DevSkillsDAO> devSkills = new HashSet<>();

    public DeveloperDAO() {
    }

    public Set<DevSkillsDAO> getDevSkills() {
        return devSkills;
    }

    public void setDevSkills(Set<DevSkillsDAO> devSkills) {
        this.devSkills = devSkills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeveloperDAO)) return false;
        DeveloperDAO that = (DeveloperDAO) o;
        return id == that.id && age == that.age && salary == that.salary && firstName.equals(that.firstName) && lastName.equals(that.lastName) && sex == that.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, sex, salary);
    }

    @Override
    public String toString() {
        return "DeveloperDAO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", comments='" + comments + '\'' +
                ", salary=" + salary +
                '}';
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

    public Set<ProjectDAO> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectDAO> projects) {
        this.projects = projects;
    }
}
