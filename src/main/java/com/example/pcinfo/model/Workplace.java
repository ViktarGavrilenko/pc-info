package com.example.pcinfo.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity(name = "workplace")
public class Workplace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "{nameNotEmpty}")
    private String name;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    private Boolean networkConnection;

    @ManyToMany(mappedBy = "workplaces")
    private Set<Person> people;

    public Workplace() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getNetworkConnection() {
        return networkConnection;
    }

    public void setNetworkConnection(Boolean networkConnection) {
        this.networkConnection = networkConnection;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}