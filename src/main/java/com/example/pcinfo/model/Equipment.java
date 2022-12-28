package com.example.pcinfo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity(name = "equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @ManyToOne
    private EquipmentType type;

    @ManyToOne
    @JoinColumn(name="workplace_id")
    private Workplace workplace;

    private String inventoryNumber;
    private String ADC;
}
