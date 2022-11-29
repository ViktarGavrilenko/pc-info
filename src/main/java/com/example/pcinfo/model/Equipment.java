package com.example.pcinfo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotBlank
    private String name;
    @ManyToOne
    private EquipmentTypes type;
    @Column(name="workplace_id")
    @ManyToOne
    private Long workplaceId;
    private String inventoryNumber;
    private String ADC;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
