package com.huellitassolidarias.huellitassolidarias_backend.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "publications")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String title;
    private String content;
    private LocalDate date;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @OneToMany(mappedBy = "publication")
    private Set<Comment> comment; // Una publicacion puede tener varios comentarios

}
