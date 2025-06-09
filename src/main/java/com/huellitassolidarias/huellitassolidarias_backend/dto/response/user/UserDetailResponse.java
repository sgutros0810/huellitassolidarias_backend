package com.huellitassolidarias.huellitassolidarias_backend.dto.response.user;

import com.huellitassolidarias.huellitassolidarias_backend.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailResponse {
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private String identification;
    private String nameShelter;
    private String address;
    private String city;
    private String country;
    private String websiteUrl;
    private String profileImageUrl;

    public UserDetailResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.identification = user.getIdentification();
        this.nameShelter = user.getNameShelter();
        this.address = user.getAddress();
        this.city = user.getCity();
        this.country = user.getCountry();
        this.websiteUrl = user.getWebsite_url();
        this.profileImageUrl = user.getProfileImageUrl();
    }
}
