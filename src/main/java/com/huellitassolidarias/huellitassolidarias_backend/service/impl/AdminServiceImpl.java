package com.huellitassolidarias.huellitassolidarias_backend.service.impl;

import com.huellitassolidarias.huellitassolidarias_backend.dto.response.admin.UserAdminResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.adoption.AdoptionDetailResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.adoption.AdoptionResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.animalreport.AnimalReportResponse;
import com.huellitassolidarias.huellitassolidarias_backend.dto.response.post.PostResponse;
import com.huellitassolidarias.huellitassolidarias_backend.entity.*;
import com.huellitassolidarias.huellitassolidarias_backend.enums.Role;
import com.huellitassolidarias.huellitassolidarias_backend.mapper.AdoptionMapper;
import com.huellitassolidarias.huellitassolidarias_backend.mapper.PostMapper;
import com.huellitassolidarias.huellitassolidarias_backend.repository.*;
import com.huellitassolidarias.huellitassolidarias_backend.service.AdminService;
import com.huellitassolidarias.huellitassolidarias_backend.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final AdoptionRepository adoptionRepository;
    private final AnimalReportRepository reportRepository;
    private final UserService userService;
    private final PostMapper postMapper;
    private final AnimalReportRepository animalReportRepository;
    private final AdoptionMapper adoptionMapper;

    @Override
    public Page<UserAdminResponse> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(UserAdminResponse::new);
    }

    @Override
    public Page<UserAdminResponse> listUsersFiltered(int page, int size, Role role, Boolean verified, Boolean verificationRequested, Boolean active, String search) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("username").ascending());
        Page<User> users = userRepository.findAllFiltered(role, verified, verificationRequested, active, search, pageable);
        return users.map(UserAdminResponse::new);
    }

    @Override
    public Page<PostResponse> getAllPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return postRepository.findAll(pageable).map(postMapper::toResponse);
    }

    @Override
    public Page<AdoptionDetailResponse> getAllAdoptions(int page, int size, String search) {
        Pageable pageable = PageRequest.of(page, size);

        if (search != null && !search.isBlank()) {
            return adoptionRepository.findByUserUsernameContainingIgnoreCase(search, pageable)
                    .map(AdoptionDetailResponse::new);
        }

        return adoptionRepository.findAll(pageable)
                .map(AdoptionDetailResponse::new);
    }

    @Override
    public Page<AnimalReportResponse> getAllAnimalReports(int page, int size, String search) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AnimalReport> reports;

        if (search != null && !search.isBlank()) {
            reports = animalReportRepository.findByUserUsernameContainingIgnoreCase(search, pageable);
        } else {
            reports = animalReportRepository.findAll(pageable);
        }

        return reports.map(AnimalReportResponse::new);
    }


    @Override
    public void verifyShelter(Long userId) {
        userService.verifyShelter(userId);
    }


    @Override
    public void toggleUserEnabled(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        user.setActive(!user.getActive());
        userRepository.save(user);
    }

    @Override
    public void updateUserRole(Long id, Role role) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        user.setRole(role);
        userRepository.save(user);
    }


    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        if (user.getRole() == Role.ADMIN) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No se puede eliminar un administrador");
        }

        commentRepository.deleteByUserId(id);
        postRepository.deleteByUserId(id);
        animalReportRepository.deleteByUserId(id);
        userRepository.delete(user);
    }

    @Override
    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Publicación no encontrada");
        }
        postRepository.deleteById(id);
    }

    @Override
    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comentario no encontrado");
        }
        commentRepository.deleteById(id);
    }

    @Override
    public void deleteAnimalReport(Long id) {
        if (!reportRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reporte no encontrado");
        }
        reportRepository.deleteById(id);
    }

    @Override
    public void deleteAdoption(Long id) {
        if (!adoptionRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Adopción no encontrada");
        }
        adoptionRepository.deleteById(id);
    }
}
