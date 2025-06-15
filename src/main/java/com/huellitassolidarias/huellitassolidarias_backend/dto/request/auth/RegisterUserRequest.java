package com.huellitassolidarias.huellitassolidarias_backend.dto.request.auth;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.huellitassolidarias.huellitassolidarias_backend.enums.City;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegisterUserRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "El apellido es obligatorio")
    private String lastname;

    @NotBlank(message = "El número de teléfono es obligatorio")
    private String phoneNumber;

    @NotBlank(message = "El usuario es obligatorio")
    @Size(min = 4, max = 16, message = "El usuario debe tener entre 4 y 16 caracteres")
    private String username;

    @NotBlank(message = "La dirección física es obligatoria")
    private String address;

    private City city;

    private String country;

    @Email(message = "Por favor, introduzca un email correcto")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "La contraseña debe contener al menos 8 caracteres, incluyendo mayúscula, minúscula y número"
    )
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

}
