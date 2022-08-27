package com.realo.estate.web.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

  private final UserDetailsService userDetailsService;
  @Value("${jwt.token.secret}")
  private String jwtSecret;
  @Value("${jwt.token.expired}")
  private long expiredIn;
  @Value("${jwt.header}")
  private String authorizationHeader;
  private static final String JWT_TOKEN_IS_EXPIRED_OR_INVALID = "JWT token is expired or invalid";
  private static final int MILLIS_IN_MINUTE = 60000;
  private static final String ROLE_KEY = "role";
  private static final String EMPTY_CREDENTIALS = "";

  @PostConstruct
  protected void init() {
    Base64.getEncoder().encodeToString(jwtSecret.getBytes());
  }

  public String resolveToken(HttpServletRequest request) {
    return request.getHeader(authorizationHeader);
  }

  public String createToken(String username, String role) {
    Claims claims = Jwts.claims()
        .setSubject(username);
    claims.put(ROLE_KEY, role);
    Date now = new Date();
    Date validity = new Date(now.getTime() + expiredIn * MILLIS_IN_MINUTE);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(SignatureAlgorithm.HS256, jwtSecret)
        .compact();
  }

  public boolean validateToken(String token) {
    try {
      Jws<Claims> claimsJws = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
      return !claimsJws.getBody().getExpiration().before(new Date());
    } catch (JwtException | IllegalArgumentException e) {
      throw new JwtAuthenticationException(JWT_TOKEN_IS_EXPIRED_OR_INVALID,
          HttpStatus.UNAUTHORIZED);
    }
  }

  public Authentication getAuthentication(String token) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
    return new UsernamePasswordAuthenticationToken(userDetails, EMPTY_CREDENTIALS,
        userDetails.getAuthorities());
  }

  private String getUsername(String token) {
    return Jwts.parser()
        .setSigningKey(jwtSecret)
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }
}