package com.example.damoserver.auth.service;

import com.example.damoserver.account.service.AccountService;
import com.example.damoserver.auth.dto.request.LoginRequest;
import com.example.damoserver.auth.dto.response.Token;
import com.example.damoserver.auth.entity.TokenVerification;
import com.example.damoserver.auth.repository.TokenVerificationRepository;
import com.example.damoserver.security.details.PrincipalDetails;
import com.example.damoserver.security.provider.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AccountService accountService;
    private final TokenVerificationRepository tokenVerificationRepository;

    //login
    public Token login(LoginRequest request) {
        String name = request.name();
        String password = request.password();
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(name, password);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        Long accountId = principalDetails.getAccount().getAccountId();
        Token token = jwtTokenProvider.generateToken(authentication);
        cacheRefreshToken(accountId,token.refreshToken());

        return token;
    }

    //refresh token으로 새로운 access token 발급
    public Token reissueAccessToken(HttpServletRequest request) {
        String refreshToken = jwtTokenProvider.resolveRefreshToken(request);
        if (refreshToken != null
            && jwtTokenProvider.validateToken(refreshToken)
            && tokenVerificationRepository.existsByRefreshToken(refreshToken)) {
            Authentication authentication =
                    jwtTokenProvider.getAuthentication(refreshToken, accountService);
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            Long accountId = principalDetails.getAccount().getAccountId();
            Token token = jwtTokenProvider.generateToken(authentication);
            cacheRefreshToken(accountId,token.refreshToken());
            return token;
        } else {
            throw new BadCredentialsException("Invalid refresh token");
        }
    }



    public void cacheRefreshToken(Long accountId, String refreshToken) {
        tokenVerificationRepository.save(new TokenVerification(accountId, refreshToken, (long) (7*24*60 * 60 * 1000)));
    }
}
