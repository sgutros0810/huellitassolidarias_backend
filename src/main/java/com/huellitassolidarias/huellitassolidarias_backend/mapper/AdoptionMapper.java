package com.huellitassolidarias.huellitassolidarias_backend.mapper;

import com.huellitassolidarias.huellitassolidarias_backend.dto.request.adoption.AdoptionRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.adoption.AdoptionResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.post.PostResponse;
import com.huellitassolidarias.huellitassolidarias_backend.entity.Adoption;
import com.huellitassolidarias.huellitassolidarias_backend.entity.Post;
import com.huellitassolidarias.huellitassolidarias_backend.entity.User;
import com.huellitassolidarias.huellitassolidarias_backend.enums.AdoptionStatus;
import com.huellitassolidarias.huellitassolidarias_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@RequiredArgsConstructor
public class AdoptionMapper {

    private final UserRepository userRepository;

    public static AdoptionResponse toAdoption(Adoption adoption) {
        return new AdoptionResponse(adoption);
    }


    public Adoption toAdoption(AdoptionRequest request, User user, String imageUrl) {
        return Adoption.builder()
                .name(request.getName())
                .species(request.getSpecies())
                .gender(request.getGender())
                .breed(request.getBreed())
                .birthDate(request.getBirthDate())
                .size(request.getSize())
                .description(request.getDescription())
                .location(request.getLocation())
                .vaccinated(request.getVaccinated())
                .sterilized(request.getSterilized())
                .status(request.getStatus() != null ? request.getStatus() : AdoptionStatus.AVAILABLE)
                .contactPhone(request.getContactPhone())
                .contactEmail(request.getContactEmail())
                .createdAt(LocalDateTime.now())
                .imageUrl(imageUrl)
                .user(user)
                .build();
    }
}
