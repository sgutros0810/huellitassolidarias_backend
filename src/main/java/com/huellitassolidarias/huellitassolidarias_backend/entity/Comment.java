package com.huellitassolidarias.huellitassolidarias_backend.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String text;
    private LocalDate date;

    //Relaciones
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    // Relación con Publicacion
    @ManyToOne
    @JoinColumn(name = "id_publication", nullable = false)
    private Publication publication;
}
