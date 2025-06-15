package com.huellitassolidarias.huellitassolidarias_backend.dto.response.adoption;

import com.huellitassolidarias.huellitassolidarias_backend.entity.Adoption;
import com.huellitassolidarias.huellitassolidarias_backend.entity.Post;
import com.huellitassolidarias.huellitassolidarias_backend.entity.User;
import com.huellitassolidarias.huellitassolidarias_backend.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
public class AdoptionResponse {

    private Long id;
    private String name;
    private Species species;
    private Gender gender;
    private String breed;
    private LocalDate birthDate;
//    private String size;
    private String description;
    private City city;
    private boolean vaccinated;
    private boolean sterilized;
    private AdoptionStatus status;
    private String contactPhone;
    private String contactEmail;
    private String imageUrl;
    private String username;
    private LocalDateTime createdAt;

    public AdoptionResponse(Adoption adoption) {
        this.id = adoption.getId();
        this.name = adoption.getName();
        this.species = adoption.getSpecies();
        this.gender = adoption.getGender();
        this.breed = adoption.getBreed();
        this.birthDate = adoption.getBirthDate();
//        this.size = adoption.getSize();
        this.description = adoption.getDescription();
        this.city = adoption.getCity();
        this.vaccinated = Boolean.TRUE.equals(adoption.getVaccinated());
        this.sterilized = Boolean.TRUE.equals(adoption.getSterilized());
        this.status = adoption.getStatus();
        this.contactPhone = adoption.getContactPhone();
        this.contactEmail = adoption.getContactEmail();
        this.imageUrl = adoption.getImageUrl();
        this.username = adoption.getUser().getUsername();
        this.createdAt = adoption.getCreatedAt();
    }

    public AdoptionResponse(User user) {
    }
}
