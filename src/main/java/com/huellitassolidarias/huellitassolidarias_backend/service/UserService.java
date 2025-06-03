package com.huellitassolidarias.huellitassolidarias_backend.service;

import com.huellitassolidarias.huellitassolidarias_backend.dto.request.user.ShelterProfileRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.request.user.UserProfileRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.User.UserProfileResponse;
import com.huellitassolidarias.huellitassolidarias_backend.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> findByEmail(String email);
    Optional<User> findByIdentification(String identification);
    boolean emailExists(String email);
    boolean identificationExists(String identification);
    User save(User user);
    UserProfileResponse getUserProfile(String email);


    @Transactional
    void updateUserProfile(User user, UserProfileRequest request);

    @Transactional
    void updateShelterProfile(User user, ShelterProfileRequest request);
}
