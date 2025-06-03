package com.huellitassolidarias.huellitassolidarias_backend.mapper;

import com.huellitassolidarias.huellitassolidarias_backend.dto.response.post.PostResponse;
import com.huellitassolidarias.huellitassolidarias_backend.entity.Post;

public class PostMapper {

    public static PostResponse toPostResponse(Post post) {
        return new PostResponse(post);
    }

}
