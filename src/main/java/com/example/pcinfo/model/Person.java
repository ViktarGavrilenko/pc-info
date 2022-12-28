package com.example.pcinfo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Введите имя")
    private String firstName;
    @NotBlank(message = "Введите фамилию")
    private String lastName;
    private String patronymic;
    @NotBlank(message = "Введите фамилию")
    private LocalDate birthday;
    @NotNull(message = "Выберите пол")
    private Gender sex;

    @ManyToMany
    @JoinTable(name = "workplace_person",
            joinColumns = @JoinColumn(name="person_id"),
            inverseJoinColumns = @JoinColumn(name="workplace_id"))
    private Set<Workplace> workplaces;
}