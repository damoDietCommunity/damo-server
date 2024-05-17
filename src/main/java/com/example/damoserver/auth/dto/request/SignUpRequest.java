package com.example.damoserver.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;


public record SignUpRequest (
        @NotNull String name,
        @NotNull @Email String email,
        @NotNull String verificationCode,
        @NotNull String password
) {
}
