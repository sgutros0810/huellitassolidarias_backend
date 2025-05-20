package com.huellitassolidarias.huellitassolidarias_backend.repository;

import com.huellitassolidarias.huellitassolidarias_backend.entity.AnimalReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnimalReportRepository extends JpaRepository<AnimalReport, Long> {
    // Buscar reportes por el usuario
    Optional<AnimalReport> findById(Long id);
}
