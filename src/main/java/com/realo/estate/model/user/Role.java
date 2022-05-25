package com.realo.estate.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@Getter
@AllArgsConstructor
@ToString
public enum Role {

    USER(Set.of(Permission.ANNOUNCEMENT_READ, Permission.ANNOUNCEMENT_WRITE,
            Permission.ESTATE_READ, Permission.ESTATE_WRITE, Permission.PROVIDER_READ)),

    AGENT(Set.of(Permission.PROVIDER_READ, Permission.PROVIDER_WRITE,
            Permission.ANNOUNCEMENT_READ, Permission.ANNOUNCEMENT_WRITE)),

    ADMIN(Set.of(Permission.USER_READ, Permission.USER_WRITE,
            Permission.ANNOUNCEMENT_READ, Permission.ANNOUNCEMENT_WRITE,
            Permission.PROVIDER_READ, Permission.PROVIDER_WRITE));

    private final Set<Permission> permissions;
}