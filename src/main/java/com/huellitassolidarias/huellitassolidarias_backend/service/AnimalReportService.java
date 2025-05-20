package com.huellitassolidarias.huellitassolidarias_backend.service;

import com.huellitassolidarias.huellitassolidarias_backend.entity.AnimalReport;
import com.huellitassolidarias.huellitassolidarias_backend.repository.AnimalReportRepository;

import java.util.Optional;

public class AnimalReportService {
    private final AnimalReportRepository animalReportRepository;

    public AnimalReportService(AnimalReportRepository reporteAnimalRepository) {
        this.animalReportRepository = reporteAnimalRepository;
    }

    // Metodo para obtener reportes por ID de usuario
    public Optional<AnimalReport> getReportsUser(Long id) {
        return animalReportRepository.findById(id);
    }
}
