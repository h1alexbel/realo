package com.realo.estate.web.controller.dto;

import com.realo.estate.domain.persistence.user.Gender;
import com.realo.estate.domain.persistence.user.Role;
import lombok.Value;

@Value
public class RegisterRequest {

    String firstName;
    String lastName;
    String login;
    String email;
    String rawPassword;
    Gender gender;
    String country;
    String phoneNumber;
    Role role;
}