package com.example.damoserver.chat.dto.response;

import java.time.LocalDateTime;

public record ChatResponse(
    String sender,
    String profileImage,
    String content,
    LocalDateTime timestamp
) {
    public static ChatResponse of(
            String sender,
            String profileImage,
            String content,
            LocalDateTime timestamp) {
        return new ChatResponse(sender, profileImage, content, timestamp);
    }
}
