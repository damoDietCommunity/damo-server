package com.example.damoserver.oauth.service;

import com.example.damoserver.account.dto.request.CreateAccountRequest;
import com.example.damoserver.account.entity.Account;
import com.example.damoserver.account.repository.AccountRepository;
import com.example.damoserver.account.service.AccountService;
import com.example.damoserver.auth.dto.request.SignUpRequest;
import com.example.damoserver.auth.service.AuthService;
import com.example.damoserver.auth.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public class OAuthService {



}
