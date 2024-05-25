package com.example.damoserver.profile.controller;

import com.example.damoserver.account.entity.Account;
import com.example.damoserver.profile.dto.request.EditProfileRequest;
import com.example.damoserver.profile.dto.response.ProfileResponse;
import com.example.damoserver.profile.service.ProfileService;
import com.example.damoserver.security.details.PrincipalDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/{accountId}")
public class ProfileController {

    private final ProfileService profileService;

    //editProfile 생성과 수정. 존재 시 프로필 수정
    @PostMapping("/profile")
    public ResponseEntity<ProfileResponse> editProfile(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestBody @Valid EditProfileRequest editProfileRequest) {

        Account account = principalDetails.getAccount();
        ProfileResponse profileResponse = profileService.editProfile(account, editProfileRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(profileResponse);

    }

}
