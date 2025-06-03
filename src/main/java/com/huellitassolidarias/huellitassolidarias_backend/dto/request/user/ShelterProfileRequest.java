package com.huellitassolidarias.huellitassolidarias_backend.dto.request.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShelterProfileRequest {

    private String profileImageUrl;
    private String email;
    private String address;
    private String phoneNumber;
    private String city;
    private String country;
    private String nameShelter;
    private String identification;
    private String websiteUrl;
    private String newPassword;

}
