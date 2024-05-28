package com.example.damoserver.profile.dto.response;

import com.example.damoserver.account.entity.Account;
import com.example.damoserver.post.dto.response.MyPostResponse;
import com.example.damoserver.profile.entity.Profile;

import java.util.List;


public record MypageResponse(
        String nickName,
        String profileImage,
        List<MyPostResponse> myPostResponses
) {
    public static MypageResponse from(Account account) {

        String nickName = account.getProfile() != null
                ? account.getProfile().getNickName()
                : account.getName();

        String profileImage = account.getProfile().getProfileImage() != null
                ? account.getProfile().getProfileImage()
                : Profile.getDefaultProfileImageUrl();

        List<MyPostResponse> myPostResponses = account.getPosts().stream()
                .map(MyPostResponse::from)
                .toList();

        return new MypageResponse(
                nickName,
                profileImage,
                myPostResponses
        );
    }
}
