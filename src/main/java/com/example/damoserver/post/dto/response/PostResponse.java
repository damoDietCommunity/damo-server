package com.example.damoserver.post.dto.response;

import com.example.damoserver.comment.dto.response.CommentResponse;
import com.example.damoserver.post.entity.Post;
import com.example.damoserver.post.entity.PostImage;

import java.time.LocalDateTime;
import java.util.List;

public record PostResponse(
        Long postId,
        String title,
        String content,
        List<PostImage> images,
        String authorName,
        List<CommentResponse> comments,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    //authorname = account > profile > nickName
    public static PostResponse from(Post post) {
        String authorName = post.getAccount().getProfile() != null
                ? post.getAccount().getProfile().getNickName()
                : post.getAccount().getName();

        List<CommentResponse> commentResponses = post.getComments().stream()
                .map(CommentResponse::from)
                .toList();

        return new PostResponse(
                post.getPostId(),
                post.getTitle(),
                post.getContent(),
                post.getImages(),
                authorName,
                commentResponses,
                post.getCreatedAt(),
                post.getUpdateAt()
        );
    }
}
