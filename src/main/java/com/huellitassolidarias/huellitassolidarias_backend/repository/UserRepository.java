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
    Page<User> findByRole(Role role, Pageable pageable);
    User findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByIdentification(String identification);
    Optional<User> findByIdentification(String identification);


    @Query("""
      SELECT u FROM User u
        WHERE u.role = 'REFUGIO'
         AND (:nameShelter     IS NULL OR LOWER(u.nameShelter) LIKE LOWER(CONCAT('%', :nameShelter, '%')))
         AND (:username IS NULL OR LOWER(u.username)   LIKE LOWER(CONCAT('%', :username, '%')))
         AND (:city     IS NULL OR LOWER(u.city)       LIKE LOWER(CONCAT('%', :city, '%')))
         AND (:country  IS NULL OR LOWER(u.country)    LIKE LOWER(CONCAT('%', :country, '%')))
      """)
    List<User> search(@Param("nameShelter") String nameShelter, @Param("username")String username, @Param("city") String city, @Param("country") String country);
}
