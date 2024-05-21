package com.example.damoserver.post.dto.request;

import java.util.List;

public record UpdatePostRequest(
        String title,
        String content,
        List<String> imageUrls
) {
}
