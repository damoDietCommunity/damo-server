package com.example.damoserver.post.dto.request;

import com.example.damoserver.comment.entity.Comment;

import java.util.List;

public record CreatePostRequest(
        String title,
        String content,
        List<String> imageUrls
) {

}
