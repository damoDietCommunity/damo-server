package com.example.damoserver.profile.dto.response;

import com.example.damoserver.account.entity.Account;
import com.example.damoserver.post.entity.Post;

import java.util.List;

public record MypageResponse(
        String nickName,
        String profileImage,
        List<Post> posts
) {

}
