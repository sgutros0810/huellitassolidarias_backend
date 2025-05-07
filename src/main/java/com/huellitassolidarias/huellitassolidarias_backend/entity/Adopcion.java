package com.huellitassolidarias.huellitassolidarias_backend.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "adopciones")
public class Adopcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String especie;

    @Column
    private String raza;

    @Column
    private Integer edad;

    @Column
    private String tamanio;

    @Column
    private Boolean vacunado;

    @Column
    private Boolean esterilizado;

    @Column
    private Boolean disponible = true;

    // Relación con Refugio
    @ManyToOne
    @JoinColumn(name = "id_refugio")
    private Usuario refugio; // Un refugio es un Usuario con rol REFUGIO

    // Relación con Adoptante
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario adoptante; // El adoptante también es un Usuario
}
