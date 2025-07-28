package com.data.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JWTResponse {
    private String accessToken;
    private String refreshToken;
    private String email;
    private String role;
}
