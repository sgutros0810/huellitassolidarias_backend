package com.huellitassolidarias.huellitassolidarias_backend.dto.response.post;

import com.huellitassolidarias.huellitassolidarias_backend.enums.Category;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDetailResponse {
    private Long id;
    private Category category;
    private String title;
    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;
    private Long userId;
}
