package com.example.pcinfo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "pc")
public class PC {
    @Id
    private Long id;
}