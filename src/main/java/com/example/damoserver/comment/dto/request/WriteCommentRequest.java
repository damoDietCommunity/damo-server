package com.example.damoserver.comment.dto.request;

import com.example.damoserver.account.entity.Account;
import com.example.damoserver.comment.entity.Comment;
import com.example.damoserver.post.entity.Post;

public record WriteCommentRequest(
    String content
) {

    public Comment toEntity(Post post, Account account) {
        return Comment.builder()
                .content(content)
                .account(account)
                .post(post)
                .build();
    }
}
