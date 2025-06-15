package com.huellitassolidarias.huellitassolidarias_backend.repository;

import com.huellitassolidarias.huellitassolidarias_backend.dto.request.animalreport.AnimalReportRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.animalreport.AnimalReportResponse;
import com.huellitassolidarias.huellitassolidarias_backend.entity.AnimalReport;
import com.huellitassolidarias.huellitassolidarias_backend.enums.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnimalReportRepository extends JpaRepository<AnimalReport, Long> {

    Optional<AnimalReport> findById(Long id);
    Page<AnimalReport> findAll(Pageable pageable);
    Page<AnimalReport> findByState(State state, Pageable pageable);

    Page<AnimalReport> findByUser_Email(String username, Pageable pageable);
    Optional<AnimalReport> findByIdAndUser_Email(Long id, String username);
    Page<AnimalReport> findByUserUsernameContainingIgnoreCase(String username, Pageable pageable);

    void deleteByUserId(Long userId);
}