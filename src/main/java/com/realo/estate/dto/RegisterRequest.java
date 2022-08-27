package com.realo.estate.dto;

import com.realo.estate.dto.validation.ValidationMessageConst;
import com.realo.estate.domain.user.Gender;
import com.realo.estate.domain.user.Role;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Value
public class RegisterRequest {

    @Size(max = 64, message = ValidationMessageConst.FIRSTNAME_BOUND)
    @NotNull(message = ValidationMessageConst.FIRSTNAME_NOT_NULL)
    String firstName;

    @Size(max = 64, message = ValidationMessageConst.LASTNAME_BOUND)
    @NotNull(message = ValidationMessageConst.LASTNAME_NOT_NULL)
    String lastName;

    @Size(min = 3, max = 64, message = ValidationMessageConst.USERNAME_BOUND)
    @NotNull(message = ValidationMessageConst.USERNAME_NOT_NULL)
    String login;

    @Email(regexp = "^[A-Za-z.]+\\w+@[A-Za-z]{2,}\\.(com|org)$")
    @Size(max = 128, message = ValidationMessageConst.EMAIL_BOUND)
    @NotNull(message = ValidationMessageConst.EMAIL_NOT_NULL)
    String email;

    @Size(min = 8, max = 128, message = ValidationMessageConst.PASSWORD_BOUND)
    @NotNull(message = ValidationMessageConst.PASSWORD_NOT_NULL)
    String rawPassword;

    @NotNull(message = ValidationMessageConst.GENDER_NOT_NULL)
    Gender gender;

    @Size(max = 32, message = ValidationMessageConst.COUNTRY_BOUND)
    @NotNull(message = ValidationMessageConst.COUNTRY_NOT_NULL)
    String country;

    @Size(min = 10, max = 16, message = ValidationMessageConst.PHONE_NUMBER_BOUND)
    @NotNull(message = ValidationMessageConst.PHONE_NUMBER_NOT_NULL)
    String phoneNumber;

    @NotNull(message = ValidationMessageConst.ROLE_NOT_NULL)
    Role role;
}