package com.huellitassolidarias.huellitassolidarias_backend.repository;


import com.huellitassolidarias.huellitassolidarias_backend.entity.Post;
import com.huellitassolidarias.huellitassolidarias_backend.enums.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    List<Post> findByCategory(Category category);

    Page<Post> findAllByUser_Id(Long userId, Pageable pageable);

    void deleteByUserId(Long userId);
}
