package com.example.damoserver.auth.dto.response;

import com.example.damoserver.account.entity.Account;
import com.example.damoserver.account.entity.Role;

public record SignUpResponse(
        //아이디와 역할 반환. 프론트와 논의 후 수정
        Long AccountId,
        String name,
        Role role
) {
    public static SignUpResponse from(Account account) {
        return new SignUpResponse(
                account.getAccountId(),
                account.getName(),
                account.getRole()
        );
    }
}
