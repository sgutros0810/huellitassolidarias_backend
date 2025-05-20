package com.huellitassolidarias.huellitassolidarias_backend.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "adoptions")
public class Adoption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String species;

    @Column
    private String race;

    @Column
    private Integer age;

    @Column
    private String size;

    @Column
    private Boolean vaccinated;

    @Column
    private Boolean sterilized;

    @Column
    private Boolean forAdoption = true;

    // Relación con Refugio
    @ManyToOne
    @JoinColumn(name = "id_shelter")
    private User shelter; // Un refugio es un Usuario con rol REFUGIO

    // Relación con Adoptante
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User adopter; // El adoptante también es un Usuario
}
