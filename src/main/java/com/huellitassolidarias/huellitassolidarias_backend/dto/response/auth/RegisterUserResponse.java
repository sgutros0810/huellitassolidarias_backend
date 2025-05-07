package com.huellitassolidarias.huellitassolidarias_backend.dto.response.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RegisterUserResponse {
    private String token;
}
