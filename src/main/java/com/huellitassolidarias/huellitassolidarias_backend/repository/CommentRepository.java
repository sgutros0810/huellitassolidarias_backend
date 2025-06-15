package com.huellitassolidarias.huellitassolidarias_backend.repository;

import com.huellitassolidarias.huellitassolidarias_backend.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Buscar comentarios por publicación
    List<Comment> findByPostId(Long id);

    List<Comment> findByPostIdOrderByCreatedAtAsc(Long postId);

    void deleteByUserId(Long userId);
}
