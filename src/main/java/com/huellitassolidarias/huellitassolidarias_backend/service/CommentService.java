package com.huellitassolidarias.huellitassolidarias_backend.service;

import com.huellitassolidarias.huellitassolidarias_backend.dto.request.comment.CommentRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.comment.CommentResponse;
import com.huellitassolidarias.huellitassolidarias_backend.entity.Comment;
import com.huellitassolidarias.huellitassolidarias_backend.entity.Post;
import com.huellitassolidarias.huellitassolidarias_backend.entity.User;
import com.huellitassolidarias.huellitassolidarias_backend.repository.CommentRepository;
import com.huellitassolidarias.huellitassolidarias_backend.repository.PostRepository;
import com.huellitassolidarias.huellitassolidarias_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository,
                          PostRepository postRepository,
                          UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<CommentResponse> getCommentsByPost(Long postId) {
        return commentRepository.findByPostIdOrderByCreatedAtAsc(postId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public void addCommentToPost(Long postId, CommentRequest request, Principal principal) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post no encontrado"));
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("User no encontrado"));

        Comment comment = Comment.builder()
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .post(post)
                .user(user)
                .build();

        commentRepository.save(comment);
    }

    private CommentResponse mapToResponse(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .username(comment.getUser().getUsername())
                .build();
    }
}
