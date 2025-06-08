package com.huellitassolidarias.huellitassolidarias_backend.dto.response.adoption;

import com.huellitassolidarias.huellitassolidarias_backend.entity.Adoption;
import com.huellitassolidarias.huellitassolidarias_backend.entity.Post;
import com.huellitassolidarias.huellitassolidarias_backend.enums.Category;
import com.huellitassolidarias.huellitassolidarias_backend.enums.Species;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class AdoptionResponse {

    private Long id;
    private String name;
    private Species species;
    private String breed;
    private int age;
    private String size;
    private boolean vaccinated;
    private boolean sterilized;
    private boolean forAdoption;
    private String username;
    private LocalDateTime createdAt;



    public AdoptionResponse(Adoption adoption) {
        this.id = adoption.getId();
        this.name = adoption.getName();
        this.species = adoption.getSpecies();
        this.breed = adoption.getBreed();
        this.age = adoption.getAge();
        this.size = adoption.getSize();
        this.vaccinated = adoption.getVaccinated();
        this.sterilized = adoption.getSterilized();
        this.forAdoption = adoption.getForAdoption();
        this.username = adoption.getUser().getUsername();
        this.createdAt = adoption.getCreatedAt();
    }
}
