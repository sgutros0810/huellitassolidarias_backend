package com.huellitassolidarias.huellitassolidarias_backend.controllers;

import com.huellitassolidarias.huellitassolidarias_backend.dto.request.user.ShelterProfileRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.request.user.SheltersRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.request.user.UserProfileRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.post.PostResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.user.SheltersResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.user.UserProfileResponse;
import com.huellitassolidarias.huellitassolidarias_backend.entity.User;
import com.huellitassolidarias.huellitassolidarias_backend.enums.Role;
import com.huellitassolidarias.huellitassolidarias_backend.repository.UserRepository;
import com.huellitassolidarias.huellitassolidarias_backend.security.UserDetailsAdapter;
import com.huellitassolidarias.huellitassolidarias_backend.service.ImageService;
import com.huellitassolidarias.huellitassolidarias_backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final ImageService imageService;

    @GetMapping("/myprofile")
    public ResponseEntity<UserProfileResponse> getMyProfile(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserProfileResponse profile = userService.getUserProfile(principal.getName());
        return ResponseEntity.ok(profile);
    }


    @PutMapping("/userprofile")
    public ResponseEntity<Void> updateUserProfile(@RequestBody @Valid UserProfileRequest request, Authentication authentication) {
        User user = ((UserDetailsAdapter) authentication.getPrincipal()).getUser();
        userService.updateUserProfile(user, request);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/shelterprofile")
    public ResponseEntity<Void> updateShelterProfile(@RequestBody @Valid ShelterProfileRequest request, Authentication authentication) {
        User user = ((UserDetailsAdapter) authentication.getPrincipal()).getUser();
        userService.updateShelterProfile(user, request);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/profile-image")
    public ResponseEntity<?> updateProfileImage(@RequestParam MultipartFile image, Principal principal) throws IOException {
        User user = userService.findByEmail(principal.getName()).orElseThrow();
        String imageUrl = imageService.saveImage(image);
        user.setProfileImageUrl(imageUrl);

        userRepository.save(user);
        return ResponseEntity.ok().body(imageUrl);
    }


    @GetMapping("/shelters")
    public ResponseEntity<Page<SheltersRequest>> getAllShelters(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SheltersRequest> shelters = userRepository.findByRole(Role.REFUGIO, pageable)
                .map(SheltersRequest::new);

        return ResponseEntity.ok(shelters);
    }

}
