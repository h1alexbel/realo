package com.realo.estate.web.controller.dto;

import lombok.Value;

@Value
public class AuthenticationRequest {

    String username;
    String password;
}