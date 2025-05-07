package com.huellitassolidarias.huellitassolidarias_backend.service;

import com.huellitassolidarias.huellitassolidarias_backend.entity.ReporteAnimal;
import com.huellitassolidarias.huellitassolidarias_backend.repository.ReporteAnimalRepository;

import java.util.List;

public class ReporteAnimalService {
    private final ReporteAnimalRepository reporteAnimalRepository;

    public ReporteAnimalService(ReporteAnimalRepository reporteAnimalRepository) {
        this.reporteAnimalRepository = reporteAnimalRepository;
    }

    // Metodo para obtener reportes por ID de usuario
    public List<ReporteAnimal> obtenerReportesPorUsuario(Long usuarioId) {
        return reporteAnimalRepository.findByUsuarioId(usuarioId);
    }
}
