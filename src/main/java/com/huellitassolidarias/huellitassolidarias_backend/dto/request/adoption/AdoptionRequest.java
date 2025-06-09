package com.huellitassolidarias.huellitassolidarias_backend.dto.request.adoption;

import com.huellitassolidarias.huellitassolidarias_backend.enums.AdoptionStatus;
import com.huellitassolidarias.huellitassolidarias_backend.enums.Gender;
import com.huellitassolidarias.huellitassolidarias_backend.enums.Species;
import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AdoptionRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotNull(message = "La especie es obligatoria")
    private Species species;

    @NotNull(message = "El género es obligatorio")
    private Gender gender;

    private String breed;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha debe ser en el pasado")
    private LocalDate birthDate;

    @Size(max = 20, message = "El tamaño debe tener como máximo 20 caracteres")
    private String size;

    @Size(max = 1000, message = "La descripción no puede superar los 1000 caracteres")
    private String description;

    @Size(max = 100, message = "La ubicación no puede superar los 100 caracteres")
    private String location;

    private Boolean vaccinated;

    private Boolean sterilized;

    private AdoptionStatus status;

    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{7,15}$", message = "El número de contacto no es válido")
    private String contactPhone;

    @Email(message = "El email de contacto no es válido")
    private String contactEmail;

    private String imageUrl;

}
