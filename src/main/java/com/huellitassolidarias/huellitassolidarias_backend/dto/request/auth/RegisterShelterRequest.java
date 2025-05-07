package com.huellitassolidarias.huellitassolidarias_backend.dto.request.auth;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterShelterRequest {

    @NotBlank
    @Pattern(
            regexp = "(^\\d{8}[A-HJ-NP-TV-Z]$)|(^[ABCDEFGHJNPQRSUVW]\\d{7}[0-9A-J]$)\n",
            message = "El CIF / NIF es incorrecto"
    )
    private String identificacion;

    @NotBlank
    private String nombreEmpresa;

    @NotBlank
    private String direccionFiscal;

    @NotBlank
    private String contactoRefugio;

    @Email(message = "Por favor, introduzca un email correcto")
    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
