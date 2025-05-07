package com.huellitassolidarias.huellitassolidarias_backend.repository;

import com.huellitassolidarias.huellitassolidarias_backend.entity.ReporteAnimal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReporteAnimalRepository extends JpaRepository<ReporteAnimal, Long> {
    // Buscar reportes por el usuario
    List<ReporteAnimal> findByUsuarioId(Long id);
}
