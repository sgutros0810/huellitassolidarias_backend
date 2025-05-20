package com.huellitassolidarias.huellitassolidarias_backend.service;

import com.huellitassolidarias.huellitassolidarias_backend.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> findByEmail(String email);
    Optional<User> findByIdentification(String identification);
    boolean emailExists(String email);
    boolean identificationExists(String identification);
    User save(User user);
    //void updateUserProfile(Usuario user, UpdateProfileRequest request);

}
