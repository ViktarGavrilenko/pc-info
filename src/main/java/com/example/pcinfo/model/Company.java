package com.example.pcinfo.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;


@Entity(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "digital_number")
    @Digits(integer = 3, fraction = 0, message = "Неверный цифровой код")
    private Integer digitalNumber;

    @Column(name = "short_name")
    private String shortName;

    @OneToOne
    @JoinColumn(name = "company_types_id")
    private CompanyTypes type;

    @Column(name = "parent_id")
    private Long parentId;

    public Company() {
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getDigitalNumber() {
        return digitalNumber;
    }

    public void setDigitalNumber(Integer digitalNumber) {
        this.digitalNumber = digitalNumber;
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