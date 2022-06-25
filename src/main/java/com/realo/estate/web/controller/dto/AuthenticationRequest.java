package com.realo.estate.web.controller.dto;

import com.realo.estate.domain.dto.validation.ValidationMessageConst;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Value
public class AuthenticationRequest {

    @Size(min = 3, max = 64, message = ValidationMessageConst.USERNAME_BOUND)
    @NotNull(message = ValidationMessageConst.USERNAME_NOT_NULL)
    String username;

    @Size(min = 8, max = 128, message = ValidationMessageConst.PASSWORD_BOUND)
    @NotNull(message = ValidationMessageConst.PASSWORD_NOT_NULL)
    String password;
}