package com.example.damoserver.profile.dto.response;

import com.example.damoserver.profile.entity.Profile;

public record ProfileResponse(
        String nickName,
        String profileImage
) {
    public static ProfileResponse of(Profile profile) {
        return new ProfileResponse(profile.getNickName(), profile.getProfileImage());
    }
}
