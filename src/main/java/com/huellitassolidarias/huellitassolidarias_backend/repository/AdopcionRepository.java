package com.huellitassolidarias.huellitassolidarias_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.huellitassolidarias.huellitassolidarias_backend.entity.Adopcion;

import java.util.List;

public interface AdopcionRepository extends JpaRepository<Adopcion, Long> {

    // Buscar adopciones por refugio
    List<Adopcion> findByRefugio_Id(Long refugioId);


}
