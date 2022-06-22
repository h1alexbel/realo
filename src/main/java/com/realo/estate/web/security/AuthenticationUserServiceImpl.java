package com.realo.estate.web.security;

import com.realo.estate.domain.dto.UserDto;
import com.realo.estate.exception.ResourceNotFoundException;
import com.realo.estate.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationUserServiceImpl implements AuthenticationUserService {

    private final UserService userService;
    private static final String USER_NOT_FOUND_MESSAGE = "User not found with username: ";
    private static final String USER_WAS_FOUND_BY_USERNAME = "User :{} found by username :{}";
    private static final String USER_WAS_NOT_FOUND_WITH_USERNAME = "User not found with username :{}";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserDto user = userService.findByLogin(username);
            User loadedUser = new User(user.getLogin(), user.getPassword(),
                    Collections.singletonList(user.getRole()));
            log.debug(USER_WAS_FOUND_BY_USERNAME, loadedUser, username);
            return loadedUser;
        } catch (ResourceNotFoundException e) {
            log.debug(USER_WAS_NOT_FOUND_WITH_USERNAME, username);
            throw new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE + username);
        }
    }
}