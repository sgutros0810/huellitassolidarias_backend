package com.huellitassolidarias.huellitassolidarias_backend.dto.request.adoption;

import com.huellitassolidarias.huellitassolidarias_backend.enums.Species;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdoptionRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotNull(message = "La especie es obligatoria")
    private Species species;

    private String breed;

    @PositiveOrZero(message = "La edad debe ser un número positivo")
    private Integer age;

    @Size(max = 20)
    private String size;

    private Boolean vaccinated;

    private Boolean sterilized;

    @NotNull(message = "Debe especificar si está para adopción")
    private Boolean forAdoption;

}
