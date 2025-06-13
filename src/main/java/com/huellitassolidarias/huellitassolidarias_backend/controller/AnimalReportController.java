package com.huellitassolidarias.huellitassolidarias_backend.controller;

import com.huellitassolidarias.huellitassolidarias_backend.dto.request.animalreport.AnimalReportRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.animalreport.AnimalReportResponse;
import com.huellitassolidarias.huellitassolidarias_backend.enums.State;
import com.huellitassolidarias.huellitassolidarias_backend.service.AnimalReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/animal-reports")
@RequiredArgsConstructor
public class AnimalReportController {

    private final AnimalReportService animalReportService;

    @PostMapping
    public AnimalReportResponse createReport(
            @ModelAttribute AnimalReportRequest request,
            @RequestParam (value = "image") MultipartFile image,
            @AuthenticationPrincipal UserDetails userDetails
    ) throws IOException {
        System.out.println("UserDetails: " + userDetails.getUsername());
        return animalReportService.createReport(request, image, userDetails.getUsername());
    }

    @GetMapping
    public Page<AnimalReportResponse> getAllReports(Pageable pageable) {
        return animalReportService.getAllReports(pageable);
    }

    @GetMapping("/state/{state}")
    public Page<AnimalReportResponse> getByState(
            @PathVariable State state,
            Pageable pageable
    ) {
        return animalReportService.getReportsByState(state, pageable);
    }

}
