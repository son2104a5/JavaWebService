package com.data.model.dto.request;

import lombok.Data;

@Data
public class VerifyOtpRequest {
    private String username;
    private String password;
    private String otp;
}