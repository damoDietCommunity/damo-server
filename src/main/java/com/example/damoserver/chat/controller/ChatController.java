package com.example.damoserver.chat.controller;

import com.example.damoserver.account.entity.Account;
import com.example.damoserver.chat.dto.request.ChatRequest;
import com.example.damoserver.chat.dto.response.ChatResponse;
import com.example.damoserver.chat.service.ChatService;
import com.example.damoserver.profile.entity.Profile;
import com.example.damoserver.security.details.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    private static final String DEFAULT_CHAT_ROOM = "public";

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/" + DEFAULT_CHAT_ROOM)
    public ChatResponse sendMessage(@AuthenticationPrincipal PrincipalDetails principalDetails, ChatRequest chatRequest) {
        Account account = principalDetails.getAccount();
        Profile profile = account.getProfile();

        return ChatResponse.of(profile.getNickName(),
                profile.getProfileImage(),
                chatRequest.content(),
                LocalDateTime.now());
    }

}
