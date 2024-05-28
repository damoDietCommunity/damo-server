package com.example.damoserver.post.dto.response;

import com.example.damoserver.comment.entity.Comment;
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
        List<Comment> comments,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    //authorname = account > profile > nickName
    public static PostResponse from(Post post) {
        String authorName = post.getAccount().getProfile() != null
                ? post.getAccount().getProfile().getNickName()
                : post.getAccount().getName();


        return new PostResponse(
                post.getPostId(),
                post.getTitle(),
                post.getContent(),
                post.getImages(),
                authorName,
                post.getComments(),
                post.getCreatedAt(),
                post.getUpdateAt()
        );
    }
}
