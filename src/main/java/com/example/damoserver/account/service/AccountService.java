package com.example.damoserver.account.service;

import com.example.damoserver.account.dto.request.CreateAccountRequest;
import com.example.damoserver.account.entity.Account;
import com.example.damoserver.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {
    private final AccountRepository accountRepository;

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(()->new RuntimeException("account not found"));
    }

    //id로 account 찾기
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username).orElseThrow(()->new RuntimeException("account not found"));
    }

    //id로 account 존재하는지
    public boolean existsByUsername(String username) {
        return accountRepository.findByUsername(username).isPresent();
    }

    //account create for signup
    public Account create(CreateAccountRequest request) {
        //id 중복 check
        if (existsByUsername(request.name()))
            throw new RuntimeException("account already exists");
        Account account = Account.from(request); //역할은 user 고정
        return accountRepository.save(account);
    }
}
