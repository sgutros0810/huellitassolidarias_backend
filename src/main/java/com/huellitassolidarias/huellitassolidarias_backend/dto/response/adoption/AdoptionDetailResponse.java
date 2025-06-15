package com.huellitassolidarias.huellitassolidarias_backend.dto.response.adoption;

import com.huellitassolidarias.huellitassolidarias_backend.dto.response.user.UserDetailResponse;
import com.huellitassolidarias.huellitassolidarias_backend.entity.Adoption;
import com.huellitassolidarias.huellitassolidarias_backend.enums.City;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdoptionDetailResponse {
    private Long id;
    private String name;
    private String species;
    private String gender;
    private String breed;
    private String birthDate;      // Puedes usar String o LocalDate según convenga
//    private String size;
    private String description;
    private City city;
    private Boolean vaccinated;
    private Boolean sterilized;
    private String status;         // Por ejemplo, AdoptionStatus como String
    private String contactPhone;
    private String contactEmail;
    private String imageUrl;

    private UserDetailResponse user;

    public AdoptionDetailResponse(Adoption adoption) {
        this.id = adoption.getId();
        this.name = adoption.getName();
        this.species = adoption.getSpecies().toString();
        this.gender = adoption.getGender().toString();
        this.breed = adoption.getBreed();
        this.birthDate = adoption.getBirthDate().toString(); // o formatear si LocalDate
//        this.size = adoption.getSize();
        this.description = adoption.getDescription();
        this.city = adoption.getCity();
        this.vaccinated = adoption.getVaccinated();
        this.sterilized = adoption.getSterilized();
        this.status = adoption.getStatus().toString();
        this.contactPhone = adoption.getContactPhone();
        this.contactEmail = adoption.getContactEmail();
        this.imageUrl = adoption.getImageUrl();

        this.user = new UserDetailResponse(adoption.getUser());
    }
}
