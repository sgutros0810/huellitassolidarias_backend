package com.huellitassolidarias.huellitassolidarias_backend.controllers;

import com.huellitassolidarias.huellitassolidarias_backend.dto.request.auth.LoginShelterRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.request.auth.LoginUserRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.request.auth.RegisterShelterRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.request.auth.RegisterUserRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.auth.LoginShelterResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.auth.LoginUserResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.auth.RegisterShelterResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.auth.RegisterUserResponse;
import com.huellitassolidarias.huellitassolidarias_backend.security.JwtService;
import com.huellitassolidarias.huellitassolidarias_backend.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;


    @PostMapping("/registeruser")
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody @Valid RegisterUserRequest request) {
        RegisterUserResponse response = authenticationService.registerUser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/loginuser")
    public ResponseEntity<LoginUserResponse> loginUser(@RequestBody @Valid LoginUserRequest request) {
        LoginUserResponse response = authenticationService.loginUser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/registershelter")
    public ResponseEntity<RegisterShelterResponse> registerShelter(@RequestBody @Valid RegisterShelterRequest request) {
        RegisterShelterResponse response = authenticationService.registerShelter(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/loginshelter")
    public ResponseEntity<LoginShelterResponse> loginShelter(@RequestBody @Valid LoginShelterRequest request) {
        LoginShelterResponse response = authenticationService.loginShelter(request);
        return ResponseEntity.ok(response);
    }
}
