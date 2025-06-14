package com.huellitassolidarias.huellitassolidarias_backend.entity;


import com.huellitassolidarias.huellitassolidarias_backend.enums.City;
import com.huellitassolidarias.huellitassolidarias_backend.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    // -------------- Datos en común --------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.USUARIO; // ADMIN, USUARIO, REFUGIO

    @Column(unique = true)
    @Size(min = 4, max = 30)
    private String username;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(nullable = false, unique = true)
    @Email(message = "Por favor, introduzca un correo correcto")
    private String email;

    @Column(nullable = false)
    private String password;

    private String phoneNumber;

    private String address;

    @Enumerated(EnumType.STRING)
    private City city;

    private String country;

    @Builder.Default
    private Boolean active = true;

    @CreationTimestamp
    @Column(updatable = false, name = "creation_date")
    private LocalDateTime creation_date = LocalDateTime.now();

//    @Column(nullable = true, unique = true)
//    private String socials;



    // -------------- Datos como usuario --------------
    @Column
    private String name;

    @Column
    private String lastname;

    // -------------- Datos como refugio --------------
    @Column(unique = true)
    //    @Pattern(
    //            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
    //            message = "La contraseña debe contener al menos 8 caracteres, incluyendo mayúscula, minúscula y número"
    //    )
    private String identification; // Número de Identificación Fiscal (NIF) o Código de Identificación Fiscal (CIF)

    private String nameShelter;

    @Builder.Default
    private boolean verified = false;

    @Column(nullable = true)
    private String website_url;

    @Column(nullable = true)
    @Size(max = 64)
    private String bankAccount;

    @Column(nullable = true)
    @Size(max = 14)
    private String bizum;

    @Column(nullable = true)
    @Email
    private String paypal;

    @Column(name = "donation_message", length = 500, nullable = true)
    private String donationMessage;

    private boolean verificationRequested = false;


    // -------------- Relaciones --------------
    @OneToMany(mappedBy = "user")
    private Set<Adoption> adoptions;
}
