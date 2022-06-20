package com.realo.estate.domain.dto;

import com.realo.estate.domain.persistence.user.ContactInfo;
import com.realo.estate.domain.persistence.user.Gender;
import com.realo.estate.domain.persistence.user.Role;
import com.realo.estate.domain.persistence.user.UserAddress;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Value
@Builder
public class UserDto implements Serializable {

    Long id;
    String firstName;
    String lastName;
    Gender gender;
    String login;
    String password;
    String email;
    Role role;
    UserAddress userAddress;
    ContactInfo contactInfo;
}