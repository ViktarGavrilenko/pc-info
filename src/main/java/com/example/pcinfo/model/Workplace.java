package com.example.pcinfo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
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
}