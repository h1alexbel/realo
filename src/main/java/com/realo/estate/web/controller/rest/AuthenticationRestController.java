package com.realo.estate.web.controller.rest;

import com.realo.estate.domain.dto.UserDto;
import com.realo.estate.service.UserService;
import com.realo.estate.web.controller.dto.AuthenticationRequest;
import com.realo.estate.web.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;
    private static final String TOKEN_RESPONSE_KEY = "token";
    private static final String USERNAME_RESPONSE_KEY = "username";
    private static final String INVALID_USERNAME_PASSWORD_COMBINATION = "Invalid username/password combination";

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody @Validated AuthenticationRequest authRequest) {
        try {
            String username = authRequest.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,
                    authRequest.getPassword()));
            UserDto user = userService.findByLogin(username);
            String token = tokenProvider.createToken(username, user.getRole().name());
            Map<Object, Object> response = new HashMap<>();
            response.put(USERNAME_RESPONSE_KEY, authRequest.getUsername());
            response.put(TOKEN_RESPONSE_KEY, token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(INVALID_USERNAME_PASSWORD_COMBINATION,
                    HttpStatus.FORBIDDEN);
        }
    }
}