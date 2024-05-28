package com.example.damoserver.chat.entity;

import com.example.damoserver.account.entity.Account;
import com.example.damoserver.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Chat extends BaseTimeEntity {

    @Enumerated(EnumType.STRING)
    private ChatType chatType;

    private String message;

    private String sender;

    @Builder
    public Chat(ChatType chatType, String sender) {
        this.chatType = chatType;
        this.sender = sender;
    }
}
