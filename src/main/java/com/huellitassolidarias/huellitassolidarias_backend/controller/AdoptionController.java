package com.huellitassolidarias.huellitassolidarias_backend.controller;

import com.huellitassolidarias.huellitassolidarias_backend.dto.request.adoption.AdoptionRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.adoption.AdoptionDetailResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.adoption.AdoptionResponse;
import com.huellitassolidarias.huellitassolidarias_backend.entity.User;
import com.huellitassolidarias.huellitassolidarias_backend.repository.AdoptionRepository;
import com.huellitassolidarias.huellitassolidarias_backend.repository.UserRepository;
import com.huellitassolidarias.huellitassolidarias_backend.security.UserDetailsAdapter;
import com.huellitassolidarias.huellitassolidarias_backend.service.impl.AdoptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/adoptions")
@RequiredArgsConstructor
public class AdoptionController {

    private final AdoptionService adoptionService;
    private final AdoptionRepository adoptionRepository;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> createAdoption(@Valid @ModelAttribute AdoptionRequest adoptionRequest,  @RequestParam(value = "image") MultipartFile image, Principal principal) throws IOException {
        adoptionService.saveAdoption(adoptionRequest, image, principal);
        return ResponseEntity.ok("Adopcion creada");
    }


    @GetMapping
    public ResponseEntity<List <AdoptionResponse>> getAllAdoptions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        List <AdoptionResponse> adoptions = adoptionRepository.findAll(pageable).map(AdoptionResponse::new).toList();
        return ResponseEntity.ok(adoptions);
    }

    // Detalles de un animal
    @GetMapping("/details/{adoptionId}")
    public ResponseEntity<AdoptionDetailResponse> getAdoptionDetails(@PathVariable Long adoptionId) {
        return adoptionRepository.findById(adoptionId)
              //  .filter(adoption -> adoption.getStatus() == '')
                .map(AdoptionDetailResponse::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAdoption(@PathVariable Long id, Authentication authentication) {
        User user = ((UserDetailsAdapter) authentication.getPrincipal()).getUser();
        adoptionService.deleteAdoption(id, user);
        return ResponseEntity.ok().build();
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateAdoption(@PathVariable Long id, @ModelAttribute AdoptionRequest adoptionRequest, @RequestParam(required = false) MultipartFile image, Authentication authentication) throws IOException {
        User user = ((UserDetailsAdapter) authentication.getPrincipal()).getUser();
        adoptionService.updateAdoption(id, adoptionRequest, user, image);
        return ResponseEntity.ok().build();
    }


}

