package com.example.damoserver.account.dto.request;

import com.example.damoserver.auth.dto.request.SignUpRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;

public record CreateAccountRequest(
        @NotNull String name,
        @NotNull String password
) {
    public static CreateAccountRequest from(SignUpRequest request, PasswordEncoder passwordEncoder) {
        return new CreateAccountRequest(
                request.name(),
                passwordEncoder.encode(request.password())
        );
    }
}
