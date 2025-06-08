package com.huellitassolidarias.huellitassolidarias_backend.entity;

import com.huellitassolidarias.huellitassolidarias_backend.enums.Species;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "adoptions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Adoption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Species species;

    @Column
    private String breed;

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

    @Column
    private LocalDateTime createdAt;

    private String imageUrl;

    // -------------- Relaciones --------------
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

}
