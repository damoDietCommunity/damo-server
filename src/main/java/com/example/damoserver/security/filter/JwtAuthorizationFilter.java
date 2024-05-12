package com.example.damoserver.security.filter;

import com.example.damoserver.account.service.AccountService;
import com.example.damoserver.security.provider.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthorizationFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;
    private final AccountService accountService;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        if (isReissueRequest((HttpServletRequest) request)) {
            filterChain.doFilter(request, response);
            return ;
        }
        String token = jwtTokenProvider.resolveAccessToken((HttpServletRequest) request);
        if (token != null && jwtTokenProvider.validateToken(token)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token, accountService);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private boolean isReissueRequest(HttpServletRequest request) {
        return request.getServletPath().equals("/auth/reissue");
    }
}
