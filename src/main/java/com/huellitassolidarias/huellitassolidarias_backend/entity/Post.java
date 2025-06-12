package com.huellitassolidarias.huellitassolidarias_backend.entity;

import com.huellitassolidarias.huellitassolidarias_backend.enums.Category;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Comment> comment; // Una publicacion puede tener varios comentarios

}
