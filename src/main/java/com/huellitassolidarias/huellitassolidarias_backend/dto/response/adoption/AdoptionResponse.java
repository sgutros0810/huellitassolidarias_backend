package com.huellitassolidarias.huellitassolidarias_backend.dto.response.adoption;

import com.huellitassolidarias.huellitassolidarias_backend.entity.Adoption;
import com.huellitassolidarias.huellitassolidarias_backend.entity.Post;
import com.huellitassolidarias.huellitassolidarias_backend.enums.AdoptionStatus;
import com.huellitassolidarias.huellitassolidarias_backend.enums.Category;
import com.huellitassolidarias.huellitassolidarias_backend.enums.Gender;
import com.huellitassolidarias.huellitassolidarias_backend.enums.Species;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class AdoptionResponse {

    private Long id;
    private String name;
    private Species species;
    private Gender gender;
    private String breed;
    private LocalDate birthDate;
    private String size;
    private String description;
    private String location;
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
        this.size = adoption.getSize();
        this.description = adoption.getDescription();
        this.location = adoption.getLocation();
        this.vaccinated = Boolean.TRUE.equals(adoption.getVaccinated());
        this.sterilized = Boolean.TRUE.equals(adoption.getSterilized());
        this.status = adoption.getStatus();
        this.contactPhone = adoption.getContactPhone();
        this.contactEmail = adoption.getContactEmail();
        this.imageUrl = adoption.getImageUrl();
        this.username = adoption.getUser().getUsername();
        this.createdAt = adoption.getCreatedAt();
    }
}
