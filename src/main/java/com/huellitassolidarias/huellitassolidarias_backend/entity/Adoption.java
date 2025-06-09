package com.huellitassolidarias.huellitassolidarias_backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huellitassolidarias.huellitassolidarias_backend.enums.AdoptionStatus;
import com.huellitassolidarias.huellitassolidarias_backend.enums.Gender;
import com.huellitassolidarias.huellitassolidarias_backend.enums.Species;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;
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

    @Enumerated(EnumType.STRING)
    private Gender gender; // MALE / FEMALE / UNKNOWN

    private String breed;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha debe ser en el pasado")
    private LocalDate birthDate;

    private String size;

    @Column(length = 1000)
    private String description;

    private String location;

    private Boolean vaccinated;

    private Boolean sterilized;

    @Enumerated(EnumType.STRING)
    private AdoptionStatus status = AdoptionStatus.AVAILABLE; // AVAILABLE, ADOPTED, RESERVED, UNDER_REVIEW

    private String contactPhone;

    private String contactEmail;

    private LocalDateTime createdAt  = LocalDateTime.now();

    private String imageUrl;

    // -------------- Relaciones --------------
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

}
