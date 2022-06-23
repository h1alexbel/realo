package com.realo.estate.web.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider provider;
    private static final String JWT_TOKEN_IS_EXPIRED_OR_INVALID = "JWT Token is expired or invalid";

    @Override
    public void doFilter
            (ServletRequest servletRequest,
             ServletResponse servletResponse,
             FilterChain filterChain) throws IOException, ServletException {
        try {
            String token = provider.resolveToken((HttpServletRequest) servletRequest);
            if (token != null && provider.validateToken(token)) {
                Authentication authentication = provider.getAuthentication(token);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (JwtAuthenticationException e) {
            SecurityContextHolder.clearContext();
            ((HttpServletResponse) servletResponse).sendError(e.getHttpStatus().value());
            throw new JwtAuthenticationException(JWT_TOKEN_IS_EXPIRED_OR_INVALID);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}