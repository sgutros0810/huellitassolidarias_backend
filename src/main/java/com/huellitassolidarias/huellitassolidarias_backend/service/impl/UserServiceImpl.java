package com.huellitassolidarias.huellitassolidarias_backend.service.impl;

import com.huellitassolidarias.huellitassolidarias_backend.dto.request.user.ShelterProfileRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.request.user.SheltersRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.request.user.UserProfileRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.admin.UserAdminResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.adoption.AdoptionResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.user.SheltersResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.user.UserDetailResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.user.UserProfileResponse;
import com.huellitassolidarias.huellitassolidarias_backend.entity.User;
import com.huellitassolidarias.huellitassolidarias_backend.enums.Role;
import com.huellitassolidarias.huellitassolidarias_backend.repository.AdoptionRepository;
import com.huellitassolidarias.huellitassolidarias_backend.repository.UserRepository;
import com.huellitassolidarias.huellitassolidarias_backend.security.UserDetailsAdapter;
import com.huellitassolidarias.huellitassolidarias_backend.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AdoptionRepository adoptionRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByIdentification(String identification) {
        return userRepository.findByIdentification(identification);
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean identificationExists(String identification) {
        return userRepository.existsByIdentification(identification);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> searchShelters(String nameShelter, String username, String city, String country) {
        return userRepository.findAll().stream()
                .filter(user -> user.getRole() == Role.REFUGIO)
                .filter(user -> nameShelter == null || user.getNameShelter() != null &&
                        user.getNameShelter().toLowerCase().contains(nameShelter.toLowerCase()))
                .filter(user -> username == null || user.getUsername() != null &&
                        user.getUsername().toLowerCase().contains(username.toLowerCase()))
                .filter(user -> city == null ||
                        (user.getCity() != null && user.getCity().toString().toLowerCase().contains(city.toLowerCase())))
                .filter(user -> country == null || user.getCountry() != null &&
                        user.getCountry().toLowerCase().contains(country.toLowerCase()))
                .toList();
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(usernameOrEmail)
                .or(() -> userRepository.findByUsername(usernameOrEmail))
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuario no encontrado: " + usernameOrEmail)
                );
        return new UserDetailsAdapter(user);
    }

    @Override
    public UserProfileResponse getUserProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return UserProfileResponse.builder()
                .id(user.getId())
                .profileImageUrl(user.getProfileImageUrl())
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .city(user.getCity())
                .country(user.getCountry())
                .role(user.getRole())
                .name(user.getName())
                .lastname(user.getLastname())
                .nameShelter(user.getNameShelter())
                .identification(user.getIdentification())
                .websiteUrl(user.getWebsite_url())
                .creationDate(user.getCreation_date())
                .verified(user.isVerified())
                .verificationRequested(user.isVerificationRequested())
                .bankAccount(user.getBankAccount())
                .bizum(user.getBizum())
                .paypal(user.getPaypal())
                .donationMessage(user.getDonationMessage())
                .build();
    }


    @Override
    @Transactional
    public void updateUserProfile(User user, UserProfileRequest request) {

        if (request.getEmail() != null && !request.getEmail().equals(user.getEmail())) {
            if(userRepository.existsByEmail(request.getEmail())){
                throw new IllegalArgumentException("El email ya está en uso");
            }
            user.setEmail(request.getEmail());
        }

        if (request.getPhoneNumber() != null) user.setPhoneNumber(request.getPhoneNumber());
        if (request.getAddress() != null) user.setAddress(request.getAddress());
        if (request.getCity() != null) user.setCity(request.getCity());
        if (request.getCountry() != null) user.setCountry(request.getCountry());

//        if (request.getNewPassword() != null && !request.getNewPassword().isBlank()) {
//            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
//        }

        userRepository.save(user);
    }


    @Override
    @Transactional
    public void updateShelterProfile(User user, ShelterProfileRequest request) {

        if (request.getEmail() != null && !request.getEmail().equals(user.getEmail())) {
            if(userRepository.existsByEmail(request.getEmail())){
                throw new IllegalArgumentException("El email ya está en uso");
            }
            user.setEmail(request.getEmail());
        }
        if (user.getRole() != Role.REFUGIO) {
            throw new AccessDeniedException("Solo los usuarios con rol REFUGIO pueden modificar el perfil de refugio.");
        }

        if(request.getPhoneNumber() != null) user.setPhoneNumber(request.getPhoneNumber());
        if(request.getAddress() != null) user.setAddress(request.getAddress());
        if(request.getCity() != null) user.setCity(request.getCity());
        if(request.getCountry() != null) user.setCountry(request.getCountry());

        if(request.getNameShelter() != null) user.setNameShelter(request.getNameShelter());
        if(request.getIdentification() != null) user.setIdentification(request.getIdentification());
        if(request.getWebsiteUrl() != null) user.setWebsite_url(request.getWebsiteUrl());

        if (user.isVerified()) {
            if(request.getBankAccount() != null) user.setBankAccount(request.getBankAccount());
            if(request.getBizum() != null) user.setBizum(request.getBizum());
            if(request.getPaypal() != null) user.setPaypal(request.getPaypal());
            if(request.getDonationMessage() != null) user.setDonationMessage(request.getDonationMessage());
        }


        userRepository.save(user);

    }

    public void requestShelterVerification(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (user.getRole() != Role.REFUGIO)
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Solo los refugios pueden solicitar verificación");
        user.setVerificationRequested(true);
        userRepository.save(user);
    }

    @Override
    public void verifyShelter(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        if (user.getRole() != Role.REFUGIO) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Solo se puede verificar a refugios");
        }

        user.setVerified(true);
        user.setVerificationRequested(false);
        userRepository.save(user);
    }


    //    public List<SheltersResponse> searchShelters(String nameShelter) {
//        User shelter = userRepository.findByNameShelter(nameShelter);
//
//
//    }
}
