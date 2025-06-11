package com.huellitassolidarias.huellitassolidarias_backend.repository;

import com.huellitassolidarias.huellitassolidarias_backend.entity.Adoption;
import com.huellitassolidarias.huellitassolidarias_backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {

    List<Adoption> findByUser(User user);

    //Page<Adoption> findById(Long shelterId, Pageable pageable);

    Page <Adoption> findById(Long id, Pageable pageable);
}
