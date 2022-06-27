package com.realo.estate.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.realo.estate.domain.dto.validation.ValidationMessageConst;
import com.realo.estate.domain.persistence.user.ContactInfo;
import com.realo.estate.domain.persistence.user.Gender;
import com.realo.estate.domain.persistence.user.Role;
import com.realo.estate.domain.persistence.user.UserAddress;
import lombok.Builder;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable {

    Long id;

    @Size(max = 64, message = ValidationMessageConst.FIRSTNAME_BOUND)
    @NotNull(message = ValidationMessageConst.FIRSTNAME_NOT_NULL)
    String firstName;

    @Size(max = 64, message = ValidationMessageConst.LASTNAME_BOUND)
    @NotNull(message = ValidationMessageConst.LASTNAME_NOT_NULL)
    String lastName;

    @NotNull(message = ValidationMessageConst.GENDER_NOT_NULL)
    Gender gender;

    @Size(min = 3, max = 64, message = ValidationMessageConst.USERNAME_BOUND)
    @NotNull(message = ValidationMessageConst.USERNAME_NOT_NULL)
    String login;

    @Size(min = 8, max = 128, message = ValidationMessageConst.PASSWORD_BOUND)
    @NotNull(message = ValidationMessageConst.PASSWORD_NOT_NULL)
    String password;

    @Email(regexp = "^[A-Za-z.]+\\w+@[A-Za-z]{2,}\\.(com|org)$")
    @Size(max = 128, message = ValidationMessageConst.EMAIL_BOUND)
    @NotNull(message = ValidationMessageConst.EMAIL_NOT_NULL)
    String email;

    @NotNull(message = ValidationMessageConst.ROLE_NOT_NULL)
    Role role;

    @Valid
    UserAddress userAddress;

    @Valid
    ContactInfo contactInfo;
}