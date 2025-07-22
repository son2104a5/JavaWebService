package com.data.service;

import com.data.model.dto.request.RegisterRequest;
import com.data.model.entity.User;
import com.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return "Username already exists!";
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // ✅ Mã hóa mật khẩu
        user.setEmail(request.getEmail());
        user.setStatus(true);

        userRepository.save(user);
        return "Register successfully!";
    }
}
