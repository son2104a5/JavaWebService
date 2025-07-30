package com.data.service;

import com.data.model.entity.User;
import com.data.model.entity.UserRefreshToken;
import com.data.repository.UserRefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRefreshTokenService {
    private final UserRefreshTokenRepository tokenRepo;

    public void save(User user, String token, String ip) {
        tokenRepo.deleteByUser(user);
        UserRefreshToken refreshToken = new UserRefreshToken(null, user, token, ip);
        tokenRepo.save(refreshToken);
    }

    public boolean validate(String token, String ip) {
        Optional<UserRefreshToken> t = tokenRepo.findByTokenRefesh(token);
        return t.isPresent() && t.get().getIpAddress().equals(ip);
    }

    public void deleteByUser(User user) {
        tokenRepo.deleteByUser(user);
    }

    public Optional<UserRefreshToken> findByToken(String token) {
        return tokenRepo.findByTokenRefesh(token);
    }
}
