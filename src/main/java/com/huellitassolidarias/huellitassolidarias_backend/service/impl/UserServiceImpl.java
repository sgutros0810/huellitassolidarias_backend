package com.huellitassolidarias.huellitassolidarias_backend.service.impl;

import com.huellitassolidarias.huellitassolidarias_backend.entity.User;
import com.huellitassolidarias.huellitassolidarias_backend.repository.UserRepository;
import com.huellitassolidarias.huellitassolidarias_backend.security.UserDetailsAdapter;
import com.huellitassolidarias.huellitassolidarias_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByIdentification(String identidication) {
        return Optional.empty();
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean identificationExists(String identification) {
        return userRepository.existsByIdentification(identification);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new UserDetailsAdapter(user);
    }
//    @Override
//    @Transactional
//    public void updateUserProfile(Usuario user, UpdateProfileRequest request) {
//        if (request.getNickname() != null && !request.getNickname().equals(user.getNickname())) {
//
//            if (userRepository.existsByNickname(request.getNickname())) {
//                throw new IllegalArgumentException("Ese nickname ya esta en uso");
//            }
//
//            if (user.getLastNicknameChange() == null ||
//                    user.getLastNicknameChange().isBefore(LocalDateTime.now().minusDays(30))) {
//                user.setNickname(request.getNickname());
//                user.setLastNicknameChange(LocalDateTime.now());
//            } else {
//                throw new IllegalArgumentException("Solo puedes cambiar el nickname una vez cada 30 dias");
//            }
//        }
//
//        if (request.getHeight() != null) user.setHeight(request.getHeight());
//        if (request.getWeight() != null) user.setWeight(request.getWeight());
//        if (request.getGoal() != null) user.setGoal(request.getGoal());
//        if (request.getActivityLevel() != null) user.setActivityLevel(request.getActivityLevel());
//        if (request.getBirthDate() != null) user.setBirthDate(request.getBirthDate());
//
//        if (request.getAllergenIds() != null) {
//            List<Allergen> allergens = allergenService.findAllByIds(request.getAllergenIds());
//            user.setAllergens(Set.copyOf(allergens));
//        }
//
//        if (request.getNewPassword() != null) {
//            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
//        }
//
//        userRepository.save(user);
//    }



}
