package com.huellitassolidarias.huellitassolidarias_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "animals_reports")
public class AnimalReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String location; // Ubicación donde se vio al animal por ultima vez

    @Column(nullable = false)
    private LocalDateTime reportDate; // Fecha en la que se realizó el reporte

    @Column
    private String image; // URL de la imagen del animal

    @Column(nullable = false)
    private String state; // Puede ser "Perdido", "Encontrado", etc.


    // Relaciones
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user; // guarda el usuario que realizó el reporte

}
