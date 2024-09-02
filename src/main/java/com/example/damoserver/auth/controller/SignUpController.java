package com.example.damoserver.auth.controller;

import com.example.damoserver.auth.dto.request.EmailVerificationCodeRequest;
import com.example.damoserver.auth.dto.request.SignUpRequest;
import com.example.damoserver.auth.dto.response.SignUpResponse;
import com.example.damoserver.auth.service.SignUpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth/signup")
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    /**
     * 회원 가입
     *
     * @param request 회원 가입 요청
     * @return 가입된 회원 정보(name(id), role)
     */
    @PostMapping
    public ResponseEntity<SignUpResponse> signUp
            (@RequestBody @Valid SignUpRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(signUpService.signUp(request));
    }
    /**
     * 이메일 인증 코드 전송
     *
     * @param request 이메일 인증 코드 전송 요청
     */
    @PostMapping("/email/send")
    public ResponseEntity<?> sendEmailVerificationCodeForSignUp(
            @RequestBody @Valid EmailVerificationCodeRequest request) {
        String email = request.email();
        signUpService.sendEmailVerificationCodeForSignUp(email);

        return ResponseEntity.ok().body("Code 전송");
    }
}
