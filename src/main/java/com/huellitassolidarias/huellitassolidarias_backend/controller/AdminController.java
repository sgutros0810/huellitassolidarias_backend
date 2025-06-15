package com.huellitassolidarias.huellitassolidarias_backend.controller;

import com.huellitassolidarias.huellitassolidarias_backend.dto.response.admin.UserAdminResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.adoption.AdoptionDetailResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.adoption.AdoptionResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.animalreport.AnimalReportResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.post.PostResponse;
import com.huellitassolidarias.huellitassolidarias_backend.enums.Role;
import com.huellitassolidarias.huellitassolidarias_backend.repository.PostRepository;
import com.huellitassolidarias.huellitassolidarias_backend.service.AdminService;
import com.huellitassolidarias.huellitassolidarias_backend.service.impl.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;
    private final PostService postService;
    private final PostRepository postRepository;

    @GetMapping("/users")
    public Page<UserAdminResponse> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Role role,
            @RequestParam(required = false) Boolean verified,
            @RequestParam(required = false) Boolean verificationRequested,
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) String search
    ) {
        return adminService.listUsersFiltered(page, size, role, verified, verificationRequested, active, search);
    }

    @GetMapping("/posts")
    public Page<PostResponse> getAllPosts(@RequestParam int page, @RequestParam int size) {
        return adminService.getAllPosts(page, size);
    }

    @GetMapping("/adoptions")
    public Page<AdoptionDetailResponse> getAllAdoptions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search
    ) {
        return adminService.getAllAdoptions(page, size, search);
    }

    @GetMapping("/reports")
    public Page<AnimalReportResponse> getAllAnimalReports(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search
    ) {
        return adminService.getAllAnimalReports(page, size, search);
    }



    @PutMapping("/users/{id}/verify-shelter")
    public ResponseEntity<Void> verifyShelter(@PathVariable Long id) {
        adminService.verifyShelter(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/users/{id}/role")
    public ResponseEntity<Void> updateUserRole(@PathVariable Long id, @RequestParam Role role) {
        adminService.updateUserRole(id, role);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{id}/toggle")
    public ResponseEntity<Void> toggleUserEnabled(@PathVariable Long id) {
        adminService.toggleUserEnabled(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        adminService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        adminService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/reports/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        adminService.deleteAnimalReport(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/adoptions/{id}")
    public ResponseEntity<Void> deleteAdoption(@PathVariable Long id) {
        adminService.deleteAdoption(id);
        return ResponseEntity.noContent().build();
    }
}
