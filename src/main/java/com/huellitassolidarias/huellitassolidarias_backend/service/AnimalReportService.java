package com.huellitassolidarias.huellitassolidarias_backend.service;

import com.huellitassolidarias.huellitassolidarias_backend.dto.request.animalreport.AnimalReportRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.animalreport.AnimalReportResponse;
import com.huellitassolidarias.huellitassolidarias_backend.enums.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AnimalReportService {
    AnimalReportResponse createReport(AnimalReportRequest request, MultipartFile multipartFile,String username) throws IOException;
    Page<AnimalReportResponse> getAllReports(Pageable pageable);
    Page<AnimalReportResponse> getReportsByState(State state, Pageable pageable);
    String saveImage(MultipartFile file) throws IOException;

    Page<AnimalReportResponse> getMyReports(String username, Pageable pageable);

    // para cambiar estado de un reporte tuyo
    void updateReportState(Long reportId, State newState, String username);
}
