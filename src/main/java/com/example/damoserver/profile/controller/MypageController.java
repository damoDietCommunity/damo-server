package com.example.damoserver.profile.controller;

import com.example.damoserver.profile.dto.response.MypageResponse;
import com.example.damoserver.security.details.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{accountId}")
@RequiredArgsConstructor
public class MypageController {

    @GetMapping("/mypage")
    public ResponseEntity<MypageResponse> getMypage(
            @PathVariable Long accountId,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return null;
    }

}
