package com.data.service.impl;

import com.data.model.dto.request.LoginRequest;
import com.data.model.dto.request.RegisterRequest;
import com.data.model.dto.response.JWTResponse;
import com.data.model.entity.User;
import com.data.repository.UserRepository;
import com.data.security.jwt.JwtUtils;
import com.data.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public JWTResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        String token = jwtUtils.generateJwtToken(loginRequest.getEmail());

        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();

        return JWTResponse.builder()
                .accessToken(token)
                .refreshToken("dummy-refresh-token")
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public JWTResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }

        User user = User.builder()
                .email(registerRequest.getEmail())
                .phone(registerRequest.getPhone())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role("ROLE_USER")
                .build();

        userRepository.save(user);

        String token = jwtUtils.generateJwtToken(user.getEmail());

        return JWTResponse.builder()
                .accessToken(token)
                .refreshToken("dummy-refresh-token")
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
