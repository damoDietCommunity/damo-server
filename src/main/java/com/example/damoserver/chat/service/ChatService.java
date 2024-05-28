package com.example.damoserver.chat.service;

import com.example.damoserver.account.entity.Account;
import com.example.damoserver.account.repository.AccountRepository;
import com.example.damoserver.chat.entity.Chat;
import com.example.damoserver.chat.repository.ChatRepository;
import com.example.damoserver.profile.entity.Profile;
import com.example.damoserver.profile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final ProfileRepository profileRepository;
    private final AccountRepository accountRepository;

    
}
