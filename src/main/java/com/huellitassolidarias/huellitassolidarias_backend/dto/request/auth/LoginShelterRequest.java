package com.huellitassolidarias.huellitassolidarias_backend.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginShelterRequest {

    @NotBlank
    private String identificacion;

    @NotBlank
    private String password;

}
