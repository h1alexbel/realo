package com.realo.estate.web.security.jwt;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

@Getter
public class JwtAuthenticationException extends AuthenticationException {

  private final HttpStatus httpStatus;

  public JwtAuthenticationException(String msg, HttpStatus httpStatus) {
    super(msg);
    this.httpStatus = httpStatus;
  }

  public JwtAuthenticationException(String msg) {
    super(msg);
    httpStatus = null;
  }
}