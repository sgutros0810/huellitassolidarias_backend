package com.huellitassolidarias.huellitassolidarias_backend.dto.request.auth;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegisterUserRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String lastname;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    @Size(min = 4, max = 16)
    private String username;

    @Email(message = "Por favor, introduzca un email correcto")
    @NotBlank
    private String email;

    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "La contraseña debe contener al menos 8 caracteres, incluyendo mayúscula, minúscula y número"
    )
    @NotBlank
    private String password;

}
