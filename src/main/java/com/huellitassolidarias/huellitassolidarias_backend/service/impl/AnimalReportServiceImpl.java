package com.huellitassolidarias.huellitassolidarias_backend.service.impl;

import com.huellitassolidarias.huellitassolidarias_backend.dto.request.animalreport.AnimalReportRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.animalreport.AnimalReportResponse;
import com.huellitassolidarias.huellitassolidarias_backend.entity.AnimalReport;
import com.huellitassolidarias.huellitassolidarias_backend.entity.User;
import com.huellitassolidarias.huellitassolidarias_backend.enums.State;
import com.huellitassolidarias.huellitassolidarias_backend.repository.AnimalReportRepository;
import com.huellitassolidarias.huellitassolidarias_backend.repository.UserRepository;

import com.huellitassolidarias.huellitassolidarias_backend.service.AnimalReportService;
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
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnimalReportServiceImpl implements AnimalReportService {

    private final AnimalReportRepository reportRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;


    public String saveImage (MultipartFile image) throws IOException {
        String fileName = UUID.randomUUID() + image.getOriginalFilename();
        Path path = Paths.get("uploads/reports", fileName);

        Files.createDirectories(path.getParent());
        Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        return "/uploads/reports/" + fileName;
    }

    @Override
    public AnimalReportResponse createReport(AnimalReportRequest request, MultipartFile image, String email) throws IOException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String imageUrl = null;
        if(request.getImage() != null && !request.getImage().isEmpty()) {
            imageUrl = saveImage(request.getImage());
        }

        AnimalReport report = new AnimalReport();
        report.setName(request.getName());
        report.setDescription(request.getDescription());
        report.setLocation(request.getLocation());
        report.setImageUrl(imageUrl);
        report.setReportDate(LocalDateTime.now());
        report.setState(request.getState());
        report.setContactName(request.getContactName());
        report.setContactPhone(request.getContactPhone());
        report.setUser(user);

        AnimalReport saved = reportRepository.save(report);
        return new AnimalReportResponse(saved);
    }

    @Override
    public Page<AnimalReportResponse> getAllReports(Pageable pageable) {
        return reportRepository.findAll(pageable).map(AnimalReportResponse::new);
    }

    @Override
    public Page<AnimalReportResponse> getReportsByState(State state, Pageable pageable) {
        return reportRepository.findByState(state, pageable).map(AnimalReportResponse::new);
    }

    @Override
    public Page<AnimalReportResponse> getMyReports(String username, Pageable pageable) {
        return reportRepository
                .findByUser_Email(username, pageable)
                .map(AnimalReportResponse::new);
    }

    @Override
    public void updateReportState(Long reportId, State newState, String username) {
        AnimalReport rpt = reportRepository
                .findByIdAndUser_Email(reportId, username)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado o no autorizado"));
        rpt.setState(newState);
        reportRepository.save(rpt);
    }

}
