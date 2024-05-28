package com.example.damoserver.chat.entity;

import com.example.damoserver.account.entity.Account;
import com.example.damoserver.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Chat extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long chatId;

    @Enumerated(EnumType.STRING)
    private ChatType chatType;

    private String message;

    private String sender;

    @Column(name = "profile_image")
    private String profileImage;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Builder
    public Chat(ChatType chatType, String message, String sender,String profileImage ,Account account) {
        this.chatType = chatType;
        this.message = message;
        this.sender = sender;
        this.profileImage = profileImage;
        this.account = account;
    }
}
