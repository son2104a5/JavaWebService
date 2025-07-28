package com.data.service;

import com.data.model.dto.request.LoginRequest;
import com.data.model.dto.request.RegisterRequest;
import com.data.model.dto.response.JWTResponse;
import com.data.model.entity.User;

public interface AuthService {
    User register(RegisterRequest registerRequest);
    JWTResponse login(LoginRequest loginRequest);
}
