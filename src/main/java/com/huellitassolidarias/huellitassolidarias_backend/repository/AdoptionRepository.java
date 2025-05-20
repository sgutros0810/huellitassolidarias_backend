package com.huellitassolidarias.huellitassolidarias_backend.repository;

import com.huellitassolidarias.huellitassolidarias_backend.entity.Adoption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {

    // Buscar adopciones por refugio
    //List<Adoption> findByRefugio_Id(Long refugioId);
}
