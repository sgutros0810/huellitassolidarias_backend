package com.huellitassolidarias.huellitassolidarias_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reportes_animales")
public class ReporteAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String nombreAnimal;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String ubicacion; // Ubicación donde se vio al animal por ultima vez

    @Column(nullable = false)
    private LocalDateTime fechaReporte; // Fecha en la que se realizó el reporte

    @Column
    private String imagen; // URL de la imagen del animal

    @Column(nullable = false)
    private String estado; // Puede ser "Perdido", "Encontrado", etc.


    // Relaciones
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario; // guarda el usuario que realizó el reporte

}
