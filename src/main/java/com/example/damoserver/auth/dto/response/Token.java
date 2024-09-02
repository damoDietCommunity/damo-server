package com.example.damoserver.auth.dto.response;

import lombok.Builder;

@Builder
public record Token(
        String grantType,
        String accessToken,
        String refreshToken
) {
}
