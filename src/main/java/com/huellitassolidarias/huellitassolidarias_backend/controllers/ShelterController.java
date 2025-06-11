package com.huellitassolidarias.huellitassolidarias_backend.controllers;

import com.huellitassolidarias.huellitassolidarias_backend.dto.response.adoption.AdoptionResponse;
import com.huellitassolidarias.huellitassolidarias_backend.service.AdoptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shelters")
@RequiredArgsConstructor
public class ShelterController {

    private final AdoptionService adoptionService;

    @GetMapping("/details/{shelterId}/adoptions")
    public ResponseEntity<Page<AdoptionResponse>> getAdoptionsByShelter(
            @PathVariable Long shelterId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<AdoptionResponse> adoptions = adoptionService.getAdoptionByShelter(shelterId, pageable);
        return ResponseEntity.ok(adoptions);
    }
}