package com.example.pcinfo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@Entity(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "{nameNotEmpty}")
    private String name;

    @Column(name = "digital_number")
    @Positive(message = "{typeMismatch.company.digitalNumber}")
    private Integer digitalNumber;

    @Column(name = "short_name")
    @NotEmpty(message = "{company.shortName}")
    private String shortName;

    @OneToOne
    @JoinColumn(name = "company_types_id")
    private CompanyType type;

    @Column(name = "parent_id")
    private Long parentId;
}