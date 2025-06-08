package com.huellitassolidarias.huellitassolidarias_backend.controllers;

import com.huellitassolidarias.huellitassolidarias_backend.dto.request.comment.CommentRequest;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.comment.CommentResponse;
import com.huellitassolidarias.huellitassolidarias_backend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // Comentarios de un post
    @GetMapping
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPost(postId));
    }

    // Agregar comentario
    @PostMapping
    public ResponseEntity<?> addComment(
            @PathVariable Long postId,
            @RequestBody CommentRequest request,
            Principal principal
    ) {
        commentService.addCommentToPost(postId, request, principal);
        return ResponseEntity.ok().build();
    }
}
