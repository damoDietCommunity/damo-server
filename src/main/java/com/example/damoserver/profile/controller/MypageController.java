package com.example.damoserver.profile.controller;

import com.example.damoserver.account.entity.Account;
import com.example.damoserver.profile.dto.response.MypageResponse;
import com.example.damoserver.security.details.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MypageController {

    @GetMapping
    public ResponseEntity<MypageResponse> getMypage(
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Account account = principalDetails.getAccount();

        return ResponseEntity.status(HttpStatus.OK).body(
                MypageResponse.from(account)
        );
    }

}
