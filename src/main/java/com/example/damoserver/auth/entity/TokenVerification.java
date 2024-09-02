package com.example.damoserver.auth.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.util.concurrent.TimeUnit;

@Getter
@RedisHash(value = "token_verification")
public class TokenVerification {

    @Id
    private Long accountId;

    @Indexed
    private String refreshToken;

    @TimeToLive(unit = TimeUnit.MILLISECONDS)
    private Long expiration;

    public TokenVerification(Long accountId, String refreshToken, Long expiration) {
        this.accountId = accountId;
        this.refreshToken = refreshToken;
        this.expiration = expiration;
    }
}
