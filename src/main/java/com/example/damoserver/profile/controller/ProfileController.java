package com.example.damoserver.profile.controller;

import com.example.damoserver.account.entity.Account;
import com.example.damoserver.account.service.AccountService;
import com.example.damoserver.profile.dto.request.EditProfileRequest;
import com.example.damoserver.profile.dto.response.ProfileResponse;
import com.example.damoserver.profile.service.ProfileService;
import com.example.damoserver.security.details.PrincipalDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;
    private final AccountService accountService;

    @GetMapping("/{accountId}")
    public ResponseEntity<ProfileResponse> getProfile(
            @PathVariable Long accountId
    ) {
        Account account = accountService.getAccountById(accountId);
        ProfileResponse profileResponse = profileService.getProfileByAccount(account);
        return ResponseEntity.status(HttpStatus.OK).body(profileResponse);
    }

    //editProfile 생성과 수정. 존재 시 프로필 수정
    @PostMapping("/{accountId}")
    public ResponseEntity<ProfileResponse> editProfile(
            @PathVariable Long accountId,
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestBody @Valid EditProfileRequest editProfileRequest) {

        Account account = principalDetails.getAccount();
        if (!account.getAccountId().equals(accountId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        ProfileResponse profileResponse = profileService.editProfile(account, editProfileRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(profileResponse);

    }
}
