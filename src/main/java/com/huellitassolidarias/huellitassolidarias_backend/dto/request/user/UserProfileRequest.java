package com.huellitassolidarias.huellitassolidarias_backend.dto.request.user;

import com.huellitassolidarias.huellitassolidarias_backend.enums.City;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileRequest {

    private String profileImageUrl;
    private String email;
    private String address;
    private String phoneNumber;
    private City city;
    private String country;

//    private String nameShelter;
//    private String identification;
//    private String websiteUrl;
    // private String newPassword;

}
