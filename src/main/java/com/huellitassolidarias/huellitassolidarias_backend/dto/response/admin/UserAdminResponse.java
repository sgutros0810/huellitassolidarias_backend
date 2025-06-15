package com.huellitassolidarias.huellitassolidarias_backend.dto.response.admin;

import com.huellitassolidarias.huellitassolidarias_backend.entity.User;
import com.huellitassolidarias.huellitassolidarias_backend.enums.City;
import com.huellitassolidarias.huellitassolidarias_backend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAdminResponse {

    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private String profileImageUrl;
    private Role role;
    private Boolean active;
    private LocalDateTime creationDate;

    // Datos comunes
    private String address;
    private City city;
    private String country;

    // Datos personales (usuario)
    private String name;
    private String lastname;

    // Datos de refugio
    private String identification;
    private String nameShelter;
    private Boolean verified;
    private Boolean verificationRequested;
    private String websiteUrl;
    private String bankAccount;
    private String bizum;
    private String paypal;
    private String donationMessage;

    public UserAdminResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.profileImageUrl = user.getProfileImageUrl();
        this.role = user.getRole();
        this.active = user.getActive();
        this.creationDate = user.getCreation_date();

        this.address = user.getAddress();
        this.city = user.getCity();
        this.country = user.getCountry();

        this.name = user.getName();
        this.lastname = user.getLastname();

        this.identification = user.getIdentification();
        this.nameShelter = user.getNameShelter();
        this.verified = user.isVerified();
        this.verificationRequested = user.isVerificationRequested();
        this.websiteUrl = user.getWebsite_url();
        this.bankAccount = user.getBankAccount();
        this.bizum = user.getBizum();
        this.paypal = user.getPaypal();
        this.donationMessage = user.getDonationMessage();
    }
}
