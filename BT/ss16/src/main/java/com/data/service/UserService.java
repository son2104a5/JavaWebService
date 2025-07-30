package com.data.service;

import com.data.model.entity.User;
import com.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public void register(User user) {
        if (userRepo.existsByUsername(user.getUsername()) || userRepo.existsByEmail(user.getEmail()))
            throw new RuntimeException("Username hoặc Email đã tồn tại");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIsLogin(false);
        user.setIsStatus(true);
        userRepo.save(user);
    }

    public User login(String username, String password) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy tài khoản"));

        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new BadCredentialsException("Sai mật khẩu");

        user.setIsLogin(true);
        return userRepo.save(user);
    }

    public void logout(User user) {
        user.setIsLogin(false);
        userRepo.save(user);
    }
}
