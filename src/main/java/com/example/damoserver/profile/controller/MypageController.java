package com.example.damoserver.profile.controller;

import com.example.damoserver.security.details.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MypageController {


//    @GetMapping
//    public ResponseEntity<?> getMypage(@AuthenticationPrincipal PrincipalDetails principalDetails) {
//
//    }



}
