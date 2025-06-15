package com.huellitassolidarias.huellitassolidarias_backend.repository;

import com.huellitassolidarias.huellitassolidarias_backend.entity.Adoption;
import com.huellitassolidarias.huellitassolidarias_backend.entity.User;

import com.huellitassolidarias.huellitassolidarias_backend.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Page<User> findByRole(Role role, Pageable pageable);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByIdentification(String identification);
    Optional<User> findByIdentification(String identification);

    User findByNameShelter(String nameShelter);

    @Query("""
SELECT u FROM User u
WHERE (:role IS NULL OR u.role = :role)
AND (:verified IS NULL OR u.verified = :verified)
AND (:verificationRequested IS NULL OR u.verificationRequested = :verificationRequested)
AND (:active IS NULL OR u.active = :active)
AND (:search IS NULL OR LOWER(u.username) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%')))
""")
    Page<User> findAllFiltered(
            @Param("role") Role role,
            @Param("verified") Boolean verified,
            @Param("verificationRequested") Boolean verificationRequested,
            @Param("active") Boolean active,
            @Param("search") String search,
            Pageable pageable
    );

}
