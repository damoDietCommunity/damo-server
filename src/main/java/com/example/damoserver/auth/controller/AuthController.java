package com.example.damoserver.auth.controller;

import com.example.damoserver.auth.dto.request.LoginRequest;
import com.example.damoserver.auth.dto.response.Token;
import com.example.damoserver.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    /**
     * login
     *
     * @param loginRequest 로그인 요청 정보
     * @return 로그인 한 회원의 accessToken, refreshToken
     */
    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody @Valid LoginRequest loginRequest) {
        Token token = authService.login(loginRequest);
        String authorization = token.grantType() + " " + token.accessToken();
        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.AUTHORIZATION, authorization)
                .body(token);
    }

    @PostMapping("/reissue")
    public ResponseEntity<Token> reissueAccessToken(HttpServletRequest request) {
        Token token = authService.reissueAccessToken(request);
        String authorization = token.grantType() + " " + token.accessToken();
        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.AUTHORIZATION,authorization)
                .body(token);
    }
}
