package com.example.pcinfo.model;

import javax.persistence.*;

@Entity(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "short_name")
    private String shortName;

    @OneToOne
    @JoinColumn(name = "company_types_id")
    private CompanyTypes type;

    public Company() {
    }

    public CompanyTypes getType() {
        return type;
    }

    public void setType(CompanyTypes type) {
        this.type = type;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}