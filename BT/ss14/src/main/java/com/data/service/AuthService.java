package com.data.service;

import com.data.model.entity.BlacklistedToken;
import com.data.model.entity.RefreshToken;
import com.data.model.entity.User;
import com.data.model.entity.UserRole;
import com.data.repository.BlacklistedTokenRepository;
import com.data.repository.RefreshTokenRepository;
import com.data.repository.UserRepository;
import com.data.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepo;
    private final RefreshTokenRepository refreshTokenRepo;
    private final BlacklistedTokenRepository blacklistRepo;
    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    public Map<String, String> login(String username, String password, String ip) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        User user = userRepo.findByUsername(username).orElseThrow();

        String accessToken = jwtUtils.generateAccessToken(username);
        String refreshToken = jwtUtils.generateRefreshToken(username);

        RefreshToken token = RefreshToken.builder()
                .user(user)
                .token(refreshToken)
                .expiryDate(LocalDateTime.now().plusDays(7))
                .addressIp(ip)
                .build();

        refreshTokenRepo.save(token);

        return Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken
        );
    }

    public Map<String, String> refreshToken(String refreshToken) {
        if (!jwtUtils.validateToken(refreshToken))
            throw new RuntimeException("Refresh token không hợp lệ");

        RefreshToken token = refreshTokenRepo.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy refresh token"));

        if (token.getExpiryDate().isBefore(LocalDateTime.now()))
            throw new RuntimeException("Refresh token đã hết hạn");

        String username = jwtUtils.getUsernameFromToken(refreshToken);
        String newAccessToken = jwtUtils.generateAccessToken(username);

        return Map.of("accessToken", newAccessToken);
    }

    public void logout(String accessToken, String refreshToken) {
        refreshTokenRepo.deleteByToken(refreshToken);

        blacklistRepo.save(BlacklistedToken.builder()
                .token(accessToken)
                .blacklistedAt(LocalDateTime.now())
                .build());
    }

    public void loginWithOtpRequest(String username, String password) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        User user = userRepo.findByUsername(username).orElseThrow();

        String otp = String.format("%06d", new SecureRandom().nextInt(999999));
        user.setOtp(otp);
        userRepo.save(user);

        mailService.sendOtpEmail(user.getEmail(), otp);
    }

    public Map<String, String> verifyOtp(String username, String password, String otp, String ip) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        User user = userRepo.findByUsername(username).orElseThrow();

        if (!otp.equals(user.getOtp()))
            throw new RuntimeException("OTP không đúng");

        user.setOtp(null);
        userRepo.save(user);

        List<RefreshToken> tokens = refreshTokenRepo.findByUserOrderByCreatedAtAsc(user);
        if (tokens.size() >= 2) {
            RefreshToken oldest = tokens.get(0);
            refreshTokenRepo.delete(oldest);
        }

        String accessToken = jwtUtils.generateAccessToken(username);
        String refreshToken = jwtUtils.generateRefreshToken(username);

        RefreshToken newToken = RefreshToken.builder()
                .user(user)
                .token(refreshToken)
                .createdAt(LocalDateTime.now())
                .expiryDate(LocalDateTime.now().plusDays(7))
                .addressIp(ip)
                .build();

        refreshTokenRepo.save(newToken);

        return Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken
        );
    }

    public void logoutAll(String username) {
        User user = userRepo.findByUsername(username).orElseThrow();
        refreshTokenRepo.deleteByUser(user);
    }

    public void changePassword(String username, String oldPass, String newPass) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPass));
        User user = userRepo.findByUsername(username).orElseThrow();
        user.setPassword(passwordEncoder.encode(newPass));
        userRepo.save(user);

        logoutAll(username);
    }

}
