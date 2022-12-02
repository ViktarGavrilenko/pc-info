package com.example.pcinfo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @ManyToOne
    private EquipmentTypes type;

    @ManyToOne
    @JoinColumn(name="workplace_id")
    private Workplace workplace;

    private String inventoryNumber;
    private String ADC;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
