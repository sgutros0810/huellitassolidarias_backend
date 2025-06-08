package com.huellitassolidarias.huellitassolidarias_backend.mapper;

import com.huellitassolidarias.huellitassolidarias_backend.dto.request.adoption.AdoptionRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.adoption.AdoptionResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.post.PostResponse;
import com.huellitassolidarias.huellitassolidarias_backend.entity.Adoption;
import com.huellitassolidarias.huellitassolidarias_backend.entity.Post;
import com.huellitassolidarias.huellitassolidarias_backend.entity.User;
import com.huellitassolidarias.huellitassolidarias_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AdoptionMapper {

    private final UserRepository userRepository;

    public static AdoptionResponse toAdoptionResponse(Adoption adoption) {
        return new AdoptionResponse(adoption);
    }

}
