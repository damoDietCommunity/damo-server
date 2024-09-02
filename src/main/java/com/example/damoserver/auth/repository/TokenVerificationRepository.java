package com.example.damoserver.auth.repository;

import com.example.damoserver.auth.entity.TokenVerification;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

public interface TokenVerificationRepository extends KeyValueRepository<TokenVerification, String> {
    boolean existsByRefreshToken(String refreshToken);
}
