package com.ProjectManagementSystem.model.dao;

import com.ProjectManagementSystem.config.config.HibernateDatabaseConnector;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projects")
public class ProjectsDAO implements DataAccessObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "name")
    private String name;
//    @Column(name = "customer_id")
    private CustomersDAO customer;
//    @Column(name = "company_id")
    private CompanyDAO company;
    @Column(name = "description")
    private String description;
    //    @Column(name = "id")
//    private int cost;
    @Column(name = "begin_date")
    private Instant begin_date;
    @ManyToMany(mappedBy = "projects")
    private Set<DeveloperDAO> developers = new HashSet<>();
    private int cost;

    public ProjectsDAO() {
        String hqlQuery = "SELECT SUM(developerDAO.salary) FROM DeveloperDAO developerDAO" +
                "join ProjectsDAO.developers;";
        SessionFactory sessionFactory = HibernateDatabaseConnector.getSessionFactory();
        Session session= sessionFactory.openSession();
        Query query = session.createQuery(hqlQuery);
        this.cost= query.getFirstResult();
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(Instant begin_date) {
        this.begin_date = begin_date;
    }

    public CustomersDAO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomersDAO customer) {
        this.customer = customer;
    }

    public CompanyDAO getCompany() {
        return company;
    }

    public void setCompany(CompanyDAO company) {
        this.company = company;
    }

    public Set<DeveloperDAO> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<DeveloperDAO> developers) {
        this.developers = developers;
    }
}
