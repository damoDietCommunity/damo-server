package com.example.damoserver.auth.service;

import com.example.damoserver.account.dto.request.CreateAccountRequest;
import com.example.damoserver.account.service.AccountService;
import com.example.damoserver.auth.dto.request.SignUpRequest;
import com.example.damoserver.auth.dto.response.SignUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SignUpService {
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final EmailVerificationService emailVerificationService;
    //signup request dto 파라미터로 받아서 유효하면 create
    @Transactional
    public SignUpResponse signUp(SignUpRequest request) {
        if (accountService.existsByName(request.name())) {
            throw new RuntimeException("Username already exists");
        }
        //이메일 인증 확인 로직
        emailVerificationService.verifyCode(request.email(), request.verificationCode());
        return SignUpResponse.from(
                accountService.create(CreateAccountRequest.from(request, passwordEncoder))
        );
    }

    @Transactional
    public void sendEmailVerificationCodeForSignUp(String email) {

        if (accountService.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }
        //하고나서 emailVerficationService호출해서 메일 인증코드 보내기 수행.
        emailVerificationService.sendEmailVerificationCode(email);
    }
}
