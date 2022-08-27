package com.realo.estate.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Getter
@AllArgsConstructor
@ToString
public enum Role implements GrantedAuthority {

  USER(Set.of(Permission.ANNOUNCEMENT_READ, Permission.ANNOUNCEMENT_WRITE,
      Permission.ESTATE_READ, Permission.ESTATE_WRITE, Permission.PROVIDER_READ)),

  AGENT(Set.of(Permission.PROVIDER_READ, Permission.PROVIDER_WRITE,
      Permission.ANNOUNCEMENT_READ, Permission.ANNOUNCEMENT_WRITE)),

  ADMIN(Set.of(Permission.USER_READ, Permission.USER_WRITE,
      Permission.ANNOUNCEMENT_READ, Permission.ANNOUNCEMENT_WRITE,
      Permission.PROVIDER_READ, Permission.PROVIDER_WRITE));

  private final Set<Permission> permissions;

  @Override
  public String getAuthority() {
    return name();
  }
}