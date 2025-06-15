package com.huellitassolidarias.huellitassolidarias_backend.service;

import com.huellitassolidarias.huellitassolidarias_backend.dto.response.admin.UserAdminResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.adoption.AdoptionDetailResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.adoption.AdoptionResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.animalreport.AnimalReportResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.post.PostResponse;
import com.huellitassolidarias.huellitassolidarias_backend.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {
    Page<UserAdminResponse> getAllUsers(Pageable pageable);
    Page<UserAdminResponse> listUsersFiltered(int page, int size, Role role, Boolean verified, Boolean verificationRequested, Boolean active, String search);
    void toggleUserEnabled(Long userId);
    void updateUserRole(Long userId, Role role);
    void deleteUser(Long userId);
    void deletePost(Long postId);
    void deleteComment(Long commentId);
    void deleteAnimalReport(Long reportId);
    void deleteAdoption(Long adoptionId);
    void verifyShelter(Long userId);
    Page<AdoptionDetailResponse> getAllAdoptions(int page, int size, String search);
    Page<AnimalReportResponse> getAllAnimalReports(int page, int size, String search);


    Page<PostResponse> getAllPosts(int page, int size);
}
