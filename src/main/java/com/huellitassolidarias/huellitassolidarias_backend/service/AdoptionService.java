package com.huellitassolidarias.huellitassolidarias_backend.service;

import com.huellitassolidarias.huellitassolidarias_backend.dto.request.adoption.AdoptionRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.adoption.AdoptionResponse;
import com.huellitassolidarias.huellitassolidarias_backend.entity.Adoption;
import com.huellitassolidarias.huellitassolidarias_backend.entity.Post;
import com.huellitassolidarias.huellitassolidarias_backend.entity.User;
import com.huellitassolidarias.huellitassolidarias_backend.mapper.AdoptionMapper;
import com.huellitassolidarias.huellitassolidarias_backend.repository.AdoptionRepository;
import com.huellitassolidarias.huellitassolidarias_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
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
                .breed(adoptionRequest.getBreed())
                .age(adoptionRequest.getAge())
                .size(adoptionRequest.getSize())
                .vaccinated(adoptionRequest.getVaccinated())
                .sterilized(adoptionRequest.getSterilized())
                .forAdoption(true)
                .imageUrl(imageUrl)
                .user(user)
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

    public List<AdoptionResponse> getAdoptionByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
        return adoptionRepository.findByUser(user).stream().map(AdoptionResponse::new).toList();
    }

    public List<AdoptionResponse> getAllAdoptions(Pageable pageable){
        return adoptionRepository.findAll(pageable).stream().map(AdoptionResponse::new).toList();
    }
}