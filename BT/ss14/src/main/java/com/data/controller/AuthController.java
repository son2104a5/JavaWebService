package com.data.controller;

import com.data.model.dto.request.ChangePasswordRequest;
import com.data.model.dto.request.LoginRequest;
import com.data.model.dto.request.VerifyOtpRequest;
import com.data.model.entity.User;
import com.data.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refresh(@RequestBody Map<String, String> req) {
        String refreshToken = req.get("refreshToken");
        return ResponseEntity.ok(authService.refreshToken(refreshToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(
            @RequestHeader("Authorization") String accessTokenHeader,
            @RequestBody Map<String, String> req) {
        String accessToken = accessTokenHeader.replace("Bearer ", "");
        String refreshToken = req.get("refreshToken");
        authService.logout(accessToken, refreshToken);
        return ResponseEntity.ok("Đăng xuất thành công");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req,
                                   HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        Map<String, String> tokens = authService.login(req.getUsername(), req.getPassword(), ip);
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        authService.loginWithOtpRequest(req.getUsername(), req.getPassword());
        return ResponseEntity.ok("OTP đã được gửi đến email.");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody VerifyOtpRequest req,
                                       HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        Map<String, String> tokens = authService.verifyOtp(
                req.getUsername(), req.getPassword(), req.getOtp(), ip);
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/logout-all")
    public ResponseEntity<?> logoutAll() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        authService.logoutAll(username);
        return ResponseEntity.ok("Đã đăng xuất khỏi tất cả thiết bị.");
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest req) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        authService.changePassword(username, req.getOldPassword(), req.getNewPassword());
        return ResponseEntity.ok("Đã đổi mật khẩu và đăng xuất khỏi mọi thiết bị.");
    }
}
