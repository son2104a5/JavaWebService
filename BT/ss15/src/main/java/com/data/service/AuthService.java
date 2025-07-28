package com.data.service;

import com.data.model.dto.request.LoginRequest;
import com.data.model.dto.request.RegisterRequest;
import com.data.model.dto.response.JWTResponse;

public interface AuthService {
    JWTResponse login(LoginRequest loginRequest);
    JWTResponse register(RegisterRequest registerRequest);
}
