package com.huellitassolidarias.huellitassolidarias_backend.dto.response.user;

import com.huellitassolidarias.huellitassolidarias_backend.dto.request.user.SheltersRequest;
import com.huellitassolidarias.huellitassolidarias_backend.entity.User;
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
    private String city;
    private String country;
    private String websiteUrl;
    private String profileImageUrl;

    public SheltersResponse(User user) {
        this.id              = user.getId();
        this.nameShelter     = user.getNameShelter();
        this.identification  = user.getIdentification();
        this.city            = user.getCity();
        this.country         = user.getCountry();
        this.websiteUrl      = user.getWebsite_url();
        this.profileImageUrl = user.getProfileImageUrl();
    }
}
