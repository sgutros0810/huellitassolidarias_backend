package com.huellitassolidarias.huellitassolidarias_backend.service.impl;

import com.huellitassolidarias.huellitassolidarias_backend.dto.request.auth.LoginUserRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.request.auth.RegisterUserRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.auth.LoginUserResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.auth.RegisterUserResponse;
import com.huellitassolidarias.huellitassolidarias_backend.entity.Usuario;
import com.huellitassolidarias.huellitassolidarias_backend.enums.Role;
import com.huellitassolidarias.huellitassolidarias_backend.repository.UsuarioRepository;
import com.huellitassolidarias.huellitassolidarias_backend.security.JwtService;
import com.huellitassolidarias.huellitassolidarias_backend.service.AuthenticationService;
import com.huellitassolidarias.huellitassolidarias_backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UsuarioService userService;

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository userRepository;
    private final JwtService jwtService;

    @Override
    public RegisterUserResponse register(RegisterUserRequest request) {
        if (userService.emailExists(request.getEmail())) {
            throw new IllegalArgumentException("El email ya esta en uso");
        }

        if (userRepository.existsByNombreUsuario(request.getNombreUsuario())) {
            throw new IllegalArgumentException("Ese nickname ya esta en uso");
        }

        Usuario user = Usuario.builder()
                .nombre(request.getNombre())
                .apellidos(request.getApellidos())
                .nombreUsuario(request.getNombreUsuario())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(Role.USUARIO)
                .build();

        userService.save(user);

        String jwtToken = jwtService.generateToken(user);

        return RegisterUserResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public LoginUserResponse login(LoginUserRequest request) {
        Usuario user = userService.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Credenciales invalidas"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Credenciales invalidas");
        }

        String token = jwtService.generateToken(user);
        return LoginUserResponse.builder().token(token).build();
    }

}
