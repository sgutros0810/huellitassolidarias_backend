package com.huellitassolidarias.huellitassolidarias_backend.service;

import com.huellitassolidarias.huellitassolidarias_backend.dto.request.adoption.AdoptionRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.adoption.AdoptionResponse;
import com.huellitassolidarias.huellitassolidarias_backend.entity.Adoption;
import com.huellitassolidarias.huellitassolidarias_backend.entity.Post;
import com.huellitassolidarias.huellitassolidarias_backend.entity.User;
import com.huellitassolidarias.huellitassolidarias_backend.enums.AdoptionStatus;
import com.huellitassolidarias.huellitassolidarias_backend.enums.Role;
import com.huellitassolidarias.huellitassolidarias_backend.mapper.AdoptionMapper;
import com.huellitassolidarias.huellitassolidarias_backend.repository.AdoptionRepository;
import com.huellitassolidarias.huellitassolidarias_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdoptionService {

    private final AdoptionRepository adoptionRepository;
    private final UserRepository userRepository;
    private final AdoptionMapper adoptionMapper;

    public void saveAdoption(AdoptionRequest adoptionRequest, MultipartFile image, Principal principal) throws IOException {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
        String imageUrl = saveImage(image);
        Adoption adoption = Adoption.builder()
                .name(adoptionRequest.getName())
                .species(adoptionRequest.getSpecies())
                .gender(adoptionRequest.getGender())
                .breed(adoptionRequest.getBreed())
                .birthDate(adoptionRequest.getBirthDate())
                .size(adoptionRequest.getSize())
                .description(adoptionRequest.getDescription())
                .location(adoptionRequest.getLocation())
                .vaccinated(adoptionRequest.getVaccinated())
                .sterilized(adoptionRequest.getSterilized())
                .status(adoptionRequest.getStatus() != null ? adoptionRequest.getStatus() : AdoptionStatus.AVAILABLE)
                .contactPhone(adoptionRequest.getContactPhone())
                .contactEmail(adoptionRequest.getContactEmail())
                .imageUrl(imageUrl)
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();

        adoptionRepository.save(adoption);
    }

    public String saveImage (MultipartFile image) throws IOException {
        String fileName = UUID.randomUUID() + image.getOriginalFilename();
        Path path = Paths.get("uploads/adoptions", fileName);

        Files.createDirectories(path.getParent());
        Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        return "/uploads/adoptions/" + fileName;
    }

    public Page<AdoptionResponse> getAdoptionByShelter(Long userId, Pageable pageable) {

        User shelter = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("Refugio no encontrado"));
        if (!shelter.getRole().equals(Role.REFUGIO)) {
            throw new RuntimeException("El usuario no es un refugio");
        }
        return adoptionRepository.findById(userId, pageable).map(AdoptionResponse::new);
    }


    public Page<AdoptionResponse> getAdoptionByUser(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
        return adoptionRepository.findById(userId, pageable).map(AdoptionResponse::new);
    }


}