package com.realo.estate.model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public enum Permission {

    USER_READ("user:read"),
    USER_WRITE("user:write"),
    ANNOUNCEMENT_READ("announcement:read"),
    ANNOUNCEMENT_WRITE("announcement:write"),
    ESTATE_READ("estate:read"),
    ESTATE_WRITE("estate:write"),
    PROVIDER_READ("provider:read"),
    PROVIDER_WRITE("provider:write");

    private final String permissionName;
}