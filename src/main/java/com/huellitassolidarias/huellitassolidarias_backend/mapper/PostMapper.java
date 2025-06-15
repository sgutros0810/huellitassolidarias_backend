package com.huellitassolidarias.huellitassolidarias_backend.mapper;

import com.huellitassolidarias.huellitassolidarias_backend.dto.request.post.PostUpdateRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.post.PostResponse;
import com.huellitassolidarias.huellitassolidarias_backend.entity.Post;
import com.huellitassolidarias.huellitassolidarias_backend.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@RequiredArgsConstructor
public class PostMapper {

    public Post toEntity(PostUpdateRequest dto, User user, String imageUrl) {
        return Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .category(dto.getCategory())
                .imageUrl(imageUrl)
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();
    }

    // Actualiza los campos de una entidad Post existente con los datos del DTO.
    public void updateEntity(PostUpdateRequest postRequest, Post post, String imageUrl) {
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setCategory(postRequest.getCategory());
        if (imageUrl != null) {
            post.setImageUrl(imageUrl);
        }
//        post.setCreatedAt(postRequest);
    }


    public PostResponse toResponse(Post post) {
        return new PostResponse(
                post.getId(),
                post.getCategory(),
                post.getTitle(),
                post.getContent(),
                post.getImageUrl(),
                post.getCreatedAt(),
                post.getUser().getProfileImageUrl(),
                post.getUser().getUsername()
        );
    }


}
