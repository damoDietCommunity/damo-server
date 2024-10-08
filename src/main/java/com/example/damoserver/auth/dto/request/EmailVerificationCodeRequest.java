package com.example.damoserver.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record EmailVerificationCodeRequest(
        @NotNull @Email String email
) {
}
