package com.example.damoserver.post.dto.request;

import com.example.damoserver.post.entity.PostImage;

import java.util.List;

public record UpdatePostRequest(
        String title,
        String content,
        List<PostImage> images
) {
}
