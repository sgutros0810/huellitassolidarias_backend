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

}