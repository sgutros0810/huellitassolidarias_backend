package com.huellitassolidarias.huellitassolidarias_backend.dto.request.auth;


import com.huellitassolidarias.huellitassolidarias_backend.enums.City;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterShelterRequest {

    @NotBlank(message = "El nombre del refugio es obligatorio")
    private String nameShelter;

    @NotBlank(message = "La identificación fiscal es obligatoria")
//    @Pattern(
//            regexp = "(^\\d{8}[A-HJ-NP-TV-Z]$)|(^[ABCDEFGHJNPQRSUVW]\\d{7}[0-9A-J]$)",
//            message = "El CIF / NIF es incorrecto"
//    )
    private String identification;

    @NotBlank(message = "El nombre de usuario del refugio es obligatorio")
    @Size(min = 4, max = 16, message = "El usuario debe tener entre 4 y 16 caracteres")
    private String username;


    @NotBlank(message = "La dirección física es obligatoria")
    private String address;

    private City city;

    private String country;

    @NotBlank(message = "El número de contacto es obligatorio")
    private String phoneNumber;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Por favor, introduzca un email correcto")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "La contraseña debe contener al menos 8 caracteres, incluyendo mayúscula, minúscula y número"
    )
    private String password;

    // Campo opcional
    private String website_url;
}
