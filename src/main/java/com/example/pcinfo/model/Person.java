package com.example.pcinfo.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate birthday;
    private Gender sex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
