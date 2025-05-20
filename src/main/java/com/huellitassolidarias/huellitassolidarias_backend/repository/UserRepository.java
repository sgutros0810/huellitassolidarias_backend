package com.huellitassolidarias.huellitassolidarias_backend.repository;

import com.huellitassolidarias.huellitassolidarias_backend.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    User findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByIdentification(String identification);

}
