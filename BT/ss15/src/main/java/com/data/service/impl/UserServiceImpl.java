package com.data.service.impl;

import com.data.model.entity.Role;
import com.data.model.entity.User;
import com.data.repository.RoleRepository;
import com.data.repository.UserRepository;
import com.data.security.principle.CustomUserDetailsService;
import com.data.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    // ✅ ADMIN cập nhật quyền user khác
    @Override
    public User updateUserRole(Long id, String roleName) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy user"));

        Role role = roleRepository.findByRoleName(roleName.toUpperCase())
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy role: " + roleName));

        user.setRoles(roleName);
        return userRepository.save(user);
    }

    // ✅ Lấy thông tin user hiện tại
    @Override
    public User getCurrentUser(CustomUserDetailsService userDetails) {
        return userRepository.findById(userDetails.toUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy user hiện tại"));
    }
}
