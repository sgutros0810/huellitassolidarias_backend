package com.huellitassolidarias.huellitassolidarias_backend.controller;

import com.huellitassolidarias.huellitassolidarias_backend.dto.response.adoption.AdoptionResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.user.SheltersResponse;
import com.huellitassolidarias.huellitassolidarias_backend.repository.UserRepository;
import com.huellitassolidarias.huellitassolidarias_backend.service.impl.AdoptionService;
import com.huellitassolidarias.huellitassolidarias_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shelters")
@RequiredArgsConstructor
public class ShelterController {

    private final AdoptionService adoptionService;
    private final UserRepository userRepository;
    private final UserService userService;

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


    @GetMapping("/search")
    public ResponseEntity<List<SheltersResponse>> searchShelters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country
    ) {
        List<SheltersResponse> results = userService
                .searchShelters(name, username, city, country);
        return ResponseEntity.ok(results);
    }
}