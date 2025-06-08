package com.huellitassolidarias.huellitassolidarias_backend.controllers;

import com.huellitassolidarias.huellitassolidarias_backend.dto.request.adoption.AdoptionRequest;
import com.huellitassolidarias.huellitassolidarias_backend.entity.Adoption;
import com.huellitassolidarias.huellitassolidarias_backend.entity.User;
import com.huellitassolidarias.huellitassolidarias_backend.enums.Category;
import com.huellitassolidarias.huellitassolidarias_backend.enums.Species;
import com.huellitassolidarias.huellitassolidarias_backend.mapper.AdoptionMapper;
import com.huellitassolidarias.huellitassolidarias_backend.service.AdoptionService;
import com.huellitassolidarias.huellitassolidarias_backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/adoptions")
@RequiredArgsConstructor
public class AdoptionController {

    private final AdoptionService adoptionService;
    @PostMapping
    public ResponseEntity<?> createAdoption(@Valid @ModelAttribute AdoptionRequest adoptionRequest,  @RequestParam(value = "image") MultipartFile image, Principal principal) throws IOException {
        adoptionService.saveAdoption(adoptionRequest, image, principal);
        return ResponseEntity.ok("Adopcion creada");
    }


//    @GetMapping
//    public ResponseEntity<List<AdoptionResponse>> getAllAdoptions(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size
//    ) {
//        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
//        List<AdoptionResponse> adoptions = adoptionService.getAllAdoptions(pageable).stream()
//                .map(AdoptionMapper::toAdoptionResponse).toList();
//        return ResponseEntity.ok(adoptions);
//    }

}

