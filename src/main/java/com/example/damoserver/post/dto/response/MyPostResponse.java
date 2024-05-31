package com.example.damoserver.post.dto.response;

import com.example.damoserver.post.entity.Post;

public record MyPostResponse(
        Long postId,
        String title,
        String content,
        String authorName,
        String thumbnail
) {
    public static final String DEFAULT_IMAGE_URL = "/path/to/storage/images/default.png"; // 실제 기본 이미지 URL로 변경

    public static MyPostResponse from(Post post) {
        String authorName = post.getAccount().getProfile() != null
                ? post.getAccount().getProfile().getNickName()
                : post.getAccount().getName();

        String thumbnail = !post.getImages().isEmpty()
                ? post.getImages().get(0).getImageUrl()
                : DEFAULT_IMAGE_URL;

        return new MyPostResponse(
                post.getPostId(),
                post.getTitle(),
                post.getContent(),
                authorName,
                thumbnail
        );
    }
}
