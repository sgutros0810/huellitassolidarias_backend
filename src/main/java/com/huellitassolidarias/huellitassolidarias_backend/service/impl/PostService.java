package com.huellitassolidarias.huellitassolidarias_backend.service;

import com.huellitassolidarias.huellitassolidarias_backend.dto.request.Post.PostUpdateRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.post.PostResponse;
import com.huellitassolidarias.huellitassolidarias_backend.entity.Post;
import com.huellitassolidarias.huellitassolidarias_backend.entity.User;
import com.huellitassolidarias.huellitassolidarias_backend.enums.Category;
import com.huellitassolidarias.huellitassolidarias_backend.mapper.PostMapper;
import com.huellitassolidarias.huellitassolidarias_backend.repository.PostRepository;

import com.huellitassolidarias.huellitassolidarias_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;

    public void savePost(String title, String content, MultipartFile image, Category category, String email) throws IOException {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
        String imageUrl = saveImage(image);
        Post post = new Post();

        post.setTitle(title);
        post.setContent(content);
        post.setUser(user);
        post.setCreatedAt(LocalDateTime.now());
        post.setImageUrl(imageUrl);
        post.setCategory(category);
        postRepository.save(post);
    }

    public String saveImage (MultipartFile image) throws IOException {
        String fileName = UUID.randomUUID() + image.getOriginalFilename();
        Path path = Paths.get("uploads/", fileName);

        Files.createDirectories(path.getParent());
        Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        return "/uploads/" + fileName;
    }

    public List<Post> getPostsByCategory(Category category) {
        return postRepository.findByCategory(category);
    }

    public List<PostResponse> getPostsByUserEmail(String email, Pageable pageable) {
        User user  = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
        return postRepository.findAllByUser_Id(user.getId(), pageable)
                .stream()
                .map(PostResponse::new)
                .toList();
    }


    public PostResponse updatePost(Long postId, PostUpdateRequest postRequest, User user, MultipartFile image) throws IOException {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new RuntimeException("Post no encontrado"));

        if(!post.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tiene permisos para editar este post");
        }

        String imageUrl = post.getImageUrl();
        if(image != null && !image.isEmpty()) {
            imageUrl = saveImage(image);
        }

        postMapper.updateEntity(postRequest, post, imageUrl);
        Post updated = postRepository.save(post);
        return postMapper.toResponse(updated);

    }
}
