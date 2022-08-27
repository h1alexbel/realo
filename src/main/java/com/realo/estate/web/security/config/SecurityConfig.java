package com.realo.estate.web.security.config;

import com.realo.estate.domain.user.Role;
import com.realo.estate.web.security.jwt.JwtConfigurer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final JwtConfigurer jwtConfigurer;

  private static final String[] startEndpoints = {
      "/api/v1/auth/login"
  };

  private static final String[] publicUserPaths = {
      "/api/v1/users/{id}",
      "/api/v1/users/login"
  };

  private static final String[] publicProviderPaths = {
      "/api/v1/providers",
      "/api/v1/providers/name",
      "/api/v1/providers/{id}"
  };

  private static final String[] publicAnnouncementPaths = {
      "/api/v1/announcements",
      "/api/v1/announcements/{id}",
      "/api/v1/announcements/title",
      "/api/v1/announcements/type",
      "/api/v1/announcements/likes/user"
  };

  private static final String[] adminOnly = {
      "/v3/api-docs",
      "/swagger-ui/**",
      "/system/**"
  };

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, startEndpoints).permitAll()
        .antMatchers(HttpMethod.GET, publicUserPaths).permitAll()
        .antMatchers(HttpMethod.GET, publicProviderPaths).permitAll()
        .antMatchers(HttpMethod.GET, publicAnnouncementPaths).permitAll()
        .antMatchers(adminOnly).hasAuthority(Role.ADMIN.getAuthority())
        .anyRequest()
        .authenticated()
        .and()
        .apply(jwtConfigurer);
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }
}