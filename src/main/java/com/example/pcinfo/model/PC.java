package com.example.pcinfo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "pc")
public class PC {
    private Long id;


    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}