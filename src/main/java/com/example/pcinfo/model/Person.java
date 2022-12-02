package com.example.pcinfo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

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

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}