package com.huellitassolidarias.huellitassolidarias_backend.service.impl;

import com.huellitassolidarias.huellitassolidarias_backend.dto.request.auth.LoginShelterRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.request.auth.LoginUserRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.request.auth.RegisterShelterRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.request.auth.RegisterUserRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.auth.LoginShelterResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.auth.LoginUserResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.auth.RegisterShelterResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.auth.RegisterUserResponse;
import com.huellitassolidarias.huellitassolidarias_backend.entity.User;
import com.huellitassolidarias.huellitassolidarias_backend.enums.Role;
import com.huellitassolidarias.huellitassolidarias_backend.repository.UserRepository;
import com.huellitassolidarias.huellitassolidarias_backend.security.JwtService;
import com.huellitassolidarias.huellitassolidarias_backend.service.AuthenticationService;

import com.huellitassolidarias.huellitassolidarias_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        if (userService.emailExists(request.getEmail())) {
            throw new IllegalArgumentException("El email ya esta en uso");
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Ese nickname ya esta en uso");
        }

        User user = User.builder()
                .name(request.getName())
                .lastname(request.getLastname())
                .phoneNumber(request.getPhoneNumber())
                .username(request.getUsername())
                .address(request.getAddress())
                .city(request.getCity())
                .country(request.getCountry())
                .email(request.getEmail())
                .profileImageUrl("/uploads/default-profile.png")
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USUARIO)
                .build();
        userService.save(user);

        String jwtToken = jwtService.generateToken(user);

        return RegisterUserResponse.builder()
                .token(jwtToken)
                .build();
    }


    @Override
    public RegisterShelterResponse registerShelter(RegisterShelterRequest request) {
        if (userService.identificationExists(request.getIdentification())) {
            throw new IllegalArgumentException("El identificador ya esta en uso");
        }

//        if (userRepository.existsByNombreUsuario(request.getIdentificacionFiscal())) {
//            throw new IllegalArgumentException("Ese nickname ya esta en uso");
//        }

        User user = User.builder()
                .nameShelter(request.getNameShelter())
                .identification(request.getIdentification())
                .username(request.getUsername())
                .address(request.getAddress())
                .city(request.getCity())
                .country(request.getCountry())
                .phoneNumber(request.getPhoneNumber())
                .website_url(request.getWebsite_url())
                .email(request.getEmail())
                .profileImageUrl("/uploads/default-profile.png")
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.REFUGIO)
                .build();

        userService.save(user);

        String jwtToken = jwtService.generateToken(user);

        return RegisterShelterResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public LoginUserResponse loginUser(LoginUserRequest request) {
        User user = userService.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Credenciales invalidas"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Credenciales invalidas");
        }

        String token = jwtService.generateToken(user);
        return LoginUserResponse.builder().token(token).build();
    }

    @Override
    public LoginShelterResponse loginShelter(LoginShelterRequest request) {
//        System.out.println("IMPRIMIR --->" + request.getIdentification());
//        System.out.println("IMPRIMIR --->" + request.getPassword());

        User user = userService.findByIdentification(request.getIdentification())
                .orElseThrow(() -> new IllegalArgumentException("Credenciales invalidas"));

//        System.out.println("IMPRIMIR --->" + passwordEncoder.matches(request.getPassword(), user.getPassword()));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Credenciales invalidas");
        }

        String token = jwtService.generateToken(user);
        return LoginShelterResponse.builder().token(token).build();
    }
}
