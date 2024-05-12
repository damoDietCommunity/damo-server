package com.example.damoserver.auth.service;

import com.example.damoserver.account.dto.request.CreateAccountRequest;
import com.example.damoserver.account.entity.Account;
import com.example.damoserver.account.service.AccountService;
import com.example.damoserver.auth.dto.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SignUpService {
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder; //인텔리제이 오류인듯? security config에서 bean 등록하면 해결될것임

    //signup request dto 파라미터로 받아서 유효하면 create
    @Transactional
    public Account signUp(SignUpRequest request) {
        if (accountService.existsByName(request.name())) {
            throw new RuntimeException("Username already exists");
        }
        return accountService.create(CreateAccountRequest.from(request, passwordEncoder));
    }
}
