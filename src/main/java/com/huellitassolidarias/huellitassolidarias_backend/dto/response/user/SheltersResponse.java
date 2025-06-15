package com.huellitassolidarias.huellitassolidarias_backend.dto.response.user;

import com.huellitassolidarias.huellitassolidarias_backend.dto.request.user.SheltersRequest;
import com.huellitassolidarias.huellitassolidarias_backend.entity.User;
import com.huellitassolidarias.huellitassolidarias_backend.enums.City;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SheltersResponse {
    private Long   id;
    private String nameShelter;
    private String identification;
    private City city;
    private String country;
    private String websiteUrl;
    private String profileImageUrl;
    private boolean verified;
    private boolean verificationRequested;
    private String bankAccount;
    private String bizum;
    private String paypal;
    private String donationMessage;

    public SheltersResponse(User user) {
        this.id = user.getId();
        this.nameShelter = user.getNameShelter();
        this.identification = user.getIdentification();
        this.city = user.getCity();
        this.country = user.getCountry();
        this.websiteUrl = user.getWebsite_url();
        this.profileImageUrl = user.getProfileImageUrl();
        this.bankAccount = user.getBankAccount();
        this.bizum = user.getBizum();
        this.paypal = user.getPaypal();
        this.donationMessage = user.getDonationMessage();
        this.verified = user.isVerified();
        this.verificationRequested = user.isVerificationRequested();
    }
}
