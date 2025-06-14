package com.huellitassolidarias.huellitassolidarias_backend.dto.response.post;

import com.huellitassolidarias.huellitassolidarias_backend.entity.Post;
import com.huellitassolidarias.huellitassolidarias_backend.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class PostResponse {

    private Long id;
    private String title;
    private String content;
    private String profileImageUrl;
    private LocalDateTime createdAt;
    private String imageUrl;
    private Category category;

    private String username;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.imageUrl = post.getImageUrl();
        this.profileImageUrl = post.getUser().getProfileImageUrl();
        this.username = post.getUser().getUsername();
        this.category = post.getCategory();
    }

    public PostResponse(Long id, Category category, String title, String content, String imageUrl, LocalDateTime createdAt, String profileImageUrl, String username) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.profileImageUrl = profileImageUrl;
        this.username = username;
    }

}
