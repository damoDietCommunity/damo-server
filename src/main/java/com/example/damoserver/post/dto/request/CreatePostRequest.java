package com.example.damoserver.post.dto.request;

import com.example.damoserver.account.entity.Account;
import com.example.damoserver.comment.entity.Comment;
import com.example.damoserver.post.entity.Post;
import com.example.damoserver.post.entity.PostImage;

import java.util.List;

public record CreatePostRequest(
        String title,
        String content,
        List<PostImage> images
) {
    public static Post from(
            CreatePostRequest request,
            Account account) {
        return Post.builder()
                .title(request.title)
                .content(request.content)
                .account(account)
                .images(request.images)
                .build();
    }
}
