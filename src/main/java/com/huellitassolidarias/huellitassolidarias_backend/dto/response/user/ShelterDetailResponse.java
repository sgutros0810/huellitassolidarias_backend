package com.huellitassolidarias.huellitassolidarias_backend.dto.response.user;

import com.huellitassolidarias.huellitassolidarias_backend.entity.User;
import com.huellitassolidarias.huellitassolidarias_backend.enums.City;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShelterDetailResponse {
    private Long id;
    private String identification;
    private String phoneNumber;
    private String username;
    private String nameShelter;
    private String address;
    private City city;
    private String country;
    private String email;
    private String websiteUrl;
    private String profileImageUrl;
    private boolean verified;

    private String bankAccount;
    private String bizum;
    private String paypal;
    private String donationMessage;

    public ShelterDetailResponse(User user) {
        this.id = user.getId();
        this.phoneNumber = user.getPhoneNumber();
        this.identification = user.getIdentification();
        this.username = user.getUsername();
        this.nameShelter = user.getNameShelter();
        this.address = user.getAddress();
        this.city = user.getCity();
        this.country = user.getCountry();
        this.email = user.getEmail();
        this.websiteUrl = user.getWebsite_url();
        this.profileImageUrl = user.getProfileImageUrl();
        this.verified = user.isVerified();
        this.bankAccount = user.getBankAccount();
        this.bizum = user.getBizum();
        this.paypal = user.getPaypal();
        this.donationMessage = user.getDonationMessage();
    }

}
