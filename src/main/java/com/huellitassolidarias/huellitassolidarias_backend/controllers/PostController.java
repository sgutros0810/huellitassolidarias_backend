package com.huellitassolidarias.huellitassolidarias_backend.controllers;

import com.huellitassolidarias.huellitassolidarias_backend.dto.response.post.PostResponse;
import com.huellitassolidarias.huellitassolidarias_backend.repository.PostRepository;
import com.huellitassolidarias.huellitassolidarias_backend.repository.UserRepository;
import com.huellitassolidarias.huellitassolidarias_backend.service.PostService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @PostMapping
    public ResponseEntity<?> createPost(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam(value = "image", required = false) MultipartFile image,
            Principal principal
    ) throws IOException {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autenticado");
        }
        postService.savePost(title, content, image, principal.getName());
        return ResponseEntity.ok("Publicacion creada");
    }

    @GetMapping
    public ResponseEntity<List <PostResponse>> getAllPosts(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ){

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());


        List <PostResponse> posts = postRepository.findAll(pageable).map(PostResponse::new).toList();

        return ResponseEntity.ok(posts);
    }
}
