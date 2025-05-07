package com.huellitassolidarias.huellitassolidarias_backend.entity;


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
@Table(name = "usuarios")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role rol = Role.USUARIO; // ADMIN, USUARIO, REFUGIO

    @Column(nullable = false, unique = true)
    @Email(message = "Por favor, introduzca un correo correcto")
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true, unique = true)
    private String paginaWeb;

    @Column(nullable = true, unique = true)
    private String redesSociales;


    // Usuario básico
    @Column(unique = true)
    @NotBlank
    @Size(min = 4, max = 16)
    private String nombreUsuario;


    @Column
    private String nombre;

    @Column
    private String apellidos;

    @Column
    private String direccion;

    @Builder.Default
    private Boolean activo = true;

    @CreationTimestamp
    @Column(updatable = false, name = "fechaCreacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();



    // Usuario como refugio
    @Column(unique = true)
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "La contraseña debe contener al menos 8 caracteres, incluyendo mayúscula, minúscula y número"
    )
    private String identificacion; // Número de Identificación Fiscal (NIF) o Código de Identificación Fiscal (CIF)

    private String nombreEmpresa;

    private String direccionFiscal;

    private String contactoRefugio;



    // Relaciones
    @OneToMany(mappedBy = "adoptante")
    private Set<Adopcion> adopciones; // Un usuario puede tener varias adopciones como adoptante

    @OneToMany(mappedBy = "refugio")
    private Set<Adopcion> adopcionesRefugio; // Un usuario puede tener varias adopciones como refugio
}
