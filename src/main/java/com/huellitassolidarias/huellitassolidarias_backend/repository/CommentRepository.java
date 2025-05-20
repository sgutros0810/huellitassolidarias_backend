package com.huellitassolidarias.huellitassolidarias_backend.repository;

import com.huellitassolidarias.huellitassolidarias_backend.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Buscar comentarios por publicación
    List<Comment> findByPublicationId(Long id);
}
