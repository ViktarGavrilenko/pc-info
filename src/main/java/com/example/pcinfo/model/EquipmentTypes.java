package com.example.pcinfo.model;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name="equipment_types")
public class EquipmentTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Введите тип устройства")
    @Column(unique = true)
    private String type;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public EquipmentTypes() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
