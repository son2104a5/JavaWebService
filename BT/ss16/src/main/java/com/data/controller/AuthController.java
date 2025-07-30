package com.data.controller;

import com.data.model.entity.User;
import com.data.model.entity.UserRefreshToken;
import com.data.security.jwt.JwtService;
import com.data.service.UserRefreshTokenService;
import com.data.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;
    private final UserRefreshTokenService refreshTokenService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        userService.register(user);
        return ResponseEntity.ok("Đăng ký thành công");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> login, HttpServletRequest request) {
        User user = userService.login(login.get("username"), login.get("password"));
        String accessToken = jwtService.generateToken(user);
        String refreshToken = UUID.randomUUID().toString();
        refreshTokenService.save(user, refreshToken, request.getRemoteAddr());
        return ResponseEntity.ok(Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken
        ));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestParam String refreshToken, HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        if (refreshTokenService.validate(refreshToken, ip)) {
            User user = refreshTokenService
                    .findByToken(refreshToken)
                    .map(UserRefreshToken::getUser)
                    .orElseThrow();
            return ResponseEntity.ok(Map.of("accessToken", jwtService.generateToken(user)));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token không hợp lệ");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        userService.logout(user);
        refreshTokenService.deleteByUser(user);
        return ResponseEntity.ok("Đăng xuất thành công");
    }
}
