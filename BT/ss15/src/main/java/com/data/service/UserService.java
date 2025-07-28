package com.data.service;

import com.data.model.entity.User;
import com.data.security.principle.CustomUserDetailsService;

public interface UserService {
    User updateUserRole(Long id, String role);
    User getCurrentUser(CustomUserDetailsService userDetails);
}
