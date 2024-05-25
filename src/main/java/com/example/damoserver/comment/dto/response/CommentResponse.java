package com.example.damoserver.comment.dto.response;

import com.example.damoserver.comment.entity.Comment;

import java.time.LocalDateTime;

public record CommentResponse(
        Long commentId,
        String content,
        String author,
        LocalDateTime createdAt
) {

    // Comment 엔티티를 기반으로 CommentResponse 객체를 생성하는 정적 팩토리 메소드
    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getCommentId(),
                comment.getContent(),
                comment.getAccount().getProfile().getNickName(),
                comment.getCreatedAt()
        );
    }
}
