package com.huellitassolidarias.huellitassolidarias_backend.dto.request.post;

import com.huellitassolidarias.huellitassolidarias_backend.enums.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostUpdateRequest {

    @NotNull(message = "La categoría es obligatoria")
    private Category category;

    @NotBlank(message = "El título es obligatorio")
    @Size(min = 4, message = "El título debe tener al menos 4 caracteres")
    private String title;

    @NotBlank(message = "El contenido es obligatorio")
    @Size(min = 10, message = "El contenido debe tener al menos 10 caracteres")
    private String content;

    private LocalDate createdAt;

    private String imageUrl;

}
