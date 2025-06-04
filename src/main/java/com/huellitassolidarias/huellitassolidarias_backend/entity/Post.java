package com.huellitassolidarias.huellitassolidarias_backend.entity;

import com.huellitassolidarias.huellitassolidarias_backend.enums.Category;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String title;
    @Lob
    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;

    // Categoria del post (ADOPCION, CONSEJO, RESCATE)
    @Enumerated(EnumType.STRING)
    private Category category;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post")
    private Set<Comment> comment; // Una publicacion puede tener varios comentarios

}
