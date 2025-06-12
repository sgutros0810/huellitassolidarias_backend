package com.huellitassolidarias.huellitassolidarias_backend.controllers;

import com.huellitassolidarias.huellitassolidarias_backend.dto.response.post.PostResponse;
import com.huellitassolidarias.huellitassolidarias_backend.entity.Post;
import com.huellitassolidarias.huellitassolidarias_backend.enums.Category;
import com.huellitassolidarias.huellitassolidarias_backend.mapper.PostMapper;
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
import org.springframework.web.server.ResponseStatusException;

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
            @RequestParam Category category,
            Principal principal
    ) throws IOException {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autenticado");
        }
        postService.savePost(title, content, image, category, principal.getName());
        return ResponseEntity.ok("Publicacion creada");
    }


//    @GetMapping("/category/{category}")
//    public ResponseEntity<List<PostResponse>> getPostsByCategory(@PathVariable Category category) {
//        List<Post> posts = postService.getPostsByCategory(category);
//        List<PostResponse> dtos = posts.stream().map(PostMapper::toPostResponse).toList();
//        return ResponseEntity.ok(dtos);
//    }

//    @GetMapping("/{userId}")
//    public ResponseEntity<List<PostResponse>> getPostsByUser(@PathVariable Long userId, @RequestParam (defaultValue = "0") int page, @RequestParam (defaultValue = "10")  int size) {
//        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
//        List<PostResponse> posts = postService.getPostsByUser(userId, pageable);
//        return ResponseEntity.ok(posts);
//    }

    @GetMapping("/my-posts")
    public ResponseEntity<List<PostResponse>> getMyPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Principal principal) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        List<PostResponse> posts = postService.getPostsByUserEmail(principal.getName(), pageable);
        return ResponseEntity.ok(posts);
    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deletePost(@PathVariable Long id, Principal principal) {
//        Post post = postRepository.findById(id).orElse(null);
//        if (post == null) {
//            return ResponseEntity.notFound().build();
//        }
//        if (post.getUser().getId().equals(principal.getName())) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autenticado");
//        }
//
//        postRepository.delete(post);
//
//        return ResponseEntity.ok("Post eliminado");
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id, Principal principal) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Post no encontrado con id " + id));
        // Comprueba que quien borra sea el autor
        if (!post.getUser().getEmail().equals(principal.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("No tienes permiso para eliminar este post");
        }
        postRepository.delete(post);
        return ResponseEntity.noContent().build();
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
