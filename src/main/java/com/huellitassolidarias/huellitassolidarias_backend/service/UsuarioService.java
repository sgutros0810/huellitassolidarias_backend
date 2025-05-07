package com.huellitassolidarias.huellitassolidarias_backend.service;

import com.huellitassolidarias.huellitassolidarias_backend.entity.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UsuarioService extends UserDetailsService {
    Optional<Usuario> findByEmail(String email);
    boolean emailExists(String email);
    Usuario save(Usuario user);
    //void updateUserProfile(Usuario user, UpdateProfileRequest request);

}
