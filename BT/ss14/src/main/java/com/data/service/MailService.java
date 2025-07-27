package com.data.service;

import org.springframework.stereotype.Service;

@Service
public class MailService {
    public void sendOtpEmail(String email, String otp) {
        System.out.println("Sending OTP to " + email + ": " + otp);
    }
}
