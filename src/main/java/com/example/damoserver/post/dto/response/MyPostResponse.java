package com.example.damoserver.post.dto.response;

import com.example.damoserver.post.entity.Post;
import com.example.damoserver.post.entity.PostImage;

import java.util.List;

public record MyPostResponse(
        String title,
        String content,
        String authorName,
        String thumbnail
) {
    public static final String DEFAULT_IMAGE_URL = "/path/to/default.png"; // 실제 기본 이미지 URL로 변경

    public static MyPostResponse from(Post post) {
        String authorName = post.getAccount().getProfile() != null
                ? post.getAccount().getProfile().getNickName()
                : post.getAccount().getName();


        String thumbnail = !post.getImages().isEmpty()
                ? post.getImages().get(0).getImageUrl()
                : DEFAULT_IMAGE_URL;

        return new MyPostResponse(
                post.getTitle(),
                post.getContent(),
                authorName,
                thumbnail
        );
    }
}
