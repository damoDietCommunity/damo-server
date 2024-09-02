package com.example.damoserver.auth.dto.request;

import jakarta.validation.constraints.NotNull;

public record LoginRequest(
        @NotNull String name,
        @NotNull String password
) {
}
