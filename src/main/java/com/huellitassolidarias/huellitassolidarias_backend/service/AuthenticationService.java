package com.huellitassolidarias.huellitassolidarias_backend.service;

import com.huellitassolidarias.huellitassolidarias_backend.dto.request.auth.LoginShelterRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.request.auth.LoginUserRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.request.auth.RegisterShelterRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.request.auth.RegisterUserRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.auth.LoginShelterResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.auth.LoginUserResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.auth.RegisterShelterResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.auth.RegisterUserResponse;

public interface AuthenticationService {
    RegisterUserResponse registerUser(RegisterUserRequest request);
    RegisterShelterResponse registerShelter(RegisterShelterRequest request);
    LoginUserResponse loginUser(LoginUserRequest request);
    LoginShelterResponse loginShelter(LoginShelterRequest request);
}
