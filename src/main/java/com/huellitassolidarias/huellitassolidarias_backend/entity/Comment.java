package com.huellitassolidarias.huellitassolidarias_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Lob
    private String content;

    private LocalDateTime createdAt = LocalDateTime.now();

    //Relaciones
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    // Relación con Publicacion
    @ManyToOne
    @JoinColumn(name = "id_post", nullable = false)
    private Post post;
}
