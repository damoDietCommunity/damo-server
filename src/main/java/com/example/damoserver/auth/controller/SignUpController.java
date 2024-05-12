package com.example.damoserver.auth.controller;

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
                .body(SignUpResponse.from(signUpService.signUp(request)));
    }
}
