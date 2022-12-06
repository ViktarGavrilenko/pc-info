package com.example.pcinfo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "company_types")
public class CompanyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Введите тип предприятия")
    @Column(unique = true)
    private String type;

    public CompanyType() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}