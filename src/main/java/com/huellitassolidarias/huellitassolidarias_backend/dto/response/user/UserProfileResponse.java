package com.huellitassolidarias.huellitassolidarias_backend.dto.response.user;

import com.huellitassolidarias.huellitassolidarias_backend.enums.City;
import com.huellitassolidarias.huellitassolidarias_backend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class UserProfileResponse {
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private String address;
    private City city;
    private String country;
    private Role role;
    private LocalDateTime creationDate;
    private String profileImageUrl;

    // USUARIO
    private String name;
    private String lastname;

    // REFUGIO
    private String identification;
    private String nameShelter;
    private String websiteUrl;
    private boolean verified;
    private boolean verificationRequested;
    private String bankAccount;
    private String bizum;
    private String paypal;
    private String donationMessage;
}
