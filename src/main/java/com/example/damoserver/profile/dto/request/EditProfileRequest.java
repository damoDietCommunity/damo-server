package com.example.damoserver.profile.dto.request;

import com.example.damoserver.account.entity.Account;
import com.example.damoserver.profile.entity.Profile;
import jakarta.validation.constraints.NotNull;

public record EditProfileRequest(
        @NotNull String nickName,
        String profileImage
) {
    public Profile toEntity() {
        return Profile.builder()
                .nickName(nickName)
                .profileImage(profileImage)
                .build();
    }
}
